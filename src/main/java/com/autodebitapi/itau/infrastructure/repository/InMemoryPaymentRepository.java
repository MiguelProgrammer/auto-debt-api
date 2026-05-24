package com.autodebitapi.itau.infrastructure.repository;

import com.autodebitapi.itau.core.domain.PaidTax;
import com.autodebitapi.itau.core.domain.PaymentReceipt;
import com.autodebitapi.itau.core.domain.PaymentStatus;
import com.autodebitapi.itau.core.domain.VehicleDebtType;
import com.autodebitapi.itau.core.port.out.PaymentRepositoryPort;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class InMemoryPaymentRepository implements PaymentRepositoryPort {

    private final Map<String, PaymentReceipt> receipts = new ConcurrentHashMap<>();
    private final Map<String, String> platesByOperation = new ConcurrentHashMap<>();

    @Override
    public PaymentReceipt save(PaymentReceipt receipt, String plate) {
        receipts.put(receipt.operationId(), receipt);
        platesByOperation.put(receipt.operationId(), plate);
        return receipt;
    }

    @Override
    public List<PaidTax> findPaidTaxes(LocalDate startDate, LocalDate endDate) {
        List<PaidTax> paidTaxes = new ArrayList<>();
        receipts.values().stream()
            .filter(receipt -> receipt.status() == PaymentStatus.PAID)
            .filter(receipt -> {
                LocalDate paidAt = receipt.createdAt().toLocalDate();
                return !paidAt.isBefore(startDate) && !paidAt.isAfter(endDate);
            })
            .forEach(receipt -> paidTaxes.add(new PaidTax(
                receipt.operationId(),
                receipt.debtId(),
                platesByOperation.getOrDefault(receipt.operationId(), "N/A"),
                inferType(receipt.debtId()),
                receipt.amount(),
                receipt.createdAt().toLocalDate()
            )));
        return paidTaxes;
    }

    private VehicleDebtType inferType(String debtId) {
        if (debtId.startsWith("IPVA")) {
            return VehicleDebtType.IPVA;
        }
        if (debtId.startsWith("DPVAT")) {
            return VehicleDebtType.DPVAT;
        }
        if (debtId.startsWith("LIC")) {
            return VehicleDebtType.LICENCIAMENTO;
        }
        return VehicleDebtType.MULTAS;
    }
}
