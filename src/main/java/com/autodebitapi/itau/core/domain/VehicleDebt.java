package com.autodebitapi.itau.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleDebt(
    String id,
    String plate,
    String renavam,
    VehicleDebtType type,
    String description,
    BigDecimal amount,
    LocalDate dueDate
) {}
