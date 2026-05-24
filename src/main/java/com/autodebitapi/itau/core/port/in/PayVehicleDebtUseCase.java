package com.autodebitapi.itau.core.port.in;

import com.autodebitapi.itau.core.domain.PaymentReceipt;

public interface PayVehicleDebtUseCase {
    PaymentReceipt pay(String debtId, String plate, String renavam, String accountId, String email, String phone);
}
