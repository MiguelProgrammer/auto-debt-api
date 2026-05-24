package com.autodebitapi.itau.core.port.out;

import com.autodebitapi.itau.core.domain.PaidTax;
import com.autodebitapi.itau.core.domain.PaymentReceipt;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRepositoryPort {
    PaymentReceipt save(PaymentReceipt receipt, String plate);
    List<PaidTax> findPaidTaxes(LocalDate startDate, LocalDate endDate);
}
