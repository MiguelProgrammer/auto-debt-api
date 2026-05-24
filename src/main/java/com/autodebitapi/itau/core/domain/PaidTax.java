package com.autodebitapi.itau.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaidTax(
    String operationId,
    String debtId,
    String plate,
    VehicleDebtType type,
    BigDecimal amount,
    LocalDate paidAt
) {}
