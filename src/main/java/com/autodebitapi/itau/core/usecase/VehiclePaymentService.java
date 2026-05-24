package com.autodebitapi.itau.core.usecase;

import com.autodebitapi.itau.core.domain.DebitResult;
import com.autodebitapi.itau.core.domain.PaymentReceipt;
import com.autodebitapi.itau.core.domain.PaymentStatus;
import com.autodebitapi.itau.core.domain.ReversalResult;
import com.autodebitapi.itau.core.domain.SettlementResult;
import com.autodebitapi.itau.core.domain.VehicleDebt;
import com.autodebitapi.itau.core.domain.exception.BusinessException;
import com.autodebitapi.itau.core.port.in.PayVehicleDebtUseCase;
import com.autodebitapi.itau.core.port.out.BankPaymentPort;
import com.autodebitapi.itau.core.port.out.NotificationPort;
import com.autodebitapi.itau.core.port.out.PaymentRepositoryPort;
import com.autodebitapi.itau.core.port.out.ProdespVehiclePort;
import java.time.OffsetDateTime;
import java.util.UUID;

public class VehiclePaymentService implements PayVehicleDebtUseCase {

    private final ProdespVehiclePort prodespVehiclePort;
    private final BankPaymentPort bankPaymentPort;
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final NotificationPort notificationPort;

    public VehiclePaymentService(
            ProdespVehiclePort prodespVehiclePort,
            BankPaymentPort bankPaymentPort,
            PaymentRepositoryPort paymentRepositoryPort,
            NotificationPort notificationPort) {
        this.prodespVehiclePort = prodespVehiclePort;
        this.bankPaymentPort = bankPaymentPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.notificationPort = notificationPort;
    }

    @Override
    public PaymentReceipt pay(String debtId, String plate, String renavam, String accountId, String email, String phone) {
        VehicleDebt debt = prodespVehiclePort.findDebtById(debtId, plate, renavam);
        DebitResult debit = bankPaymentPort.debit(accountId, debt.amount(), "Pagamento " + debt.type() + " " + debt.plate());

        if (!debit.success()) {
            PaymentReceipt failed = receipt(debt, debit.transactionId(), null, PaymentStatus.FAILED, debit.message());
            paymentRepositoryPort.save(failed, debt.plate());
            throw new BusinessException(debit.message());
        }

        SettlementResult settlement = prodespVehiclePort.settleDebt(debt);
        if (!settlement.success()) {
            ReversalResult reversal = bankPaymentPort.reverse(accountId, debt.amount(), debit.transactionId(), settlement.message());
            PaymentReceipt reversed = receipt(debt, debit.transactionId(), reversal.transactionId(), PaymentStatus.REVERSED, settlement.message());
            paymentRepositoryPort.save(reversed, debt.plate());
            notificationPort.sendSms(phone, "Erro ao pagar " + debt.type() + ". Valor estornado. Operacao: " + reversed.operationId());
            return reversed;
        }

        PaymentReceipt paid = receipt(debt, debit.transactionId(), null, PaymentStatus.PAID, "Pagamento realizado com sucesso");
        PaymentReceipt saved = paymentRepositoryPort.save(paid, debt.plate());
        notificationPort.sendEmail(email, "Comprovante de pagamento veicular", buildReceiptEmail(debt, saved, settlement.protocol()));
        return saved;
    }

    private PaymentReceipt receipt(VehicleDebt debt, String debitTransactionId, String reversalTransactionId, PaymentStatus status, String message) {
        return new PaymentReceipt(
            UUID.randomUUID().toString(),
            debt.id(),
            debitTransactionId,
            reversalTransactionId,
            status,
            debt.amount(),
            message,
            OffsetDateTime.now()
        );
    }

    private String buildReceiptEmail(VehicleDebt debt, PaymentReceipt receipt, String protocol) {
        return "Comprovante de pagamento\n"
            + "Operacao: " + receipt.operationId() + "\n"
            + "Debito: " + debt.id() + "\n"
            + "Tipo: " + debt.type() + "\n"
            + "Placa: " + debt.plate() + "\n"
            + "Valor: " + debt.amount() + "\n"
            + "Protocolo Prodesp: " + protocol;
    }
}
