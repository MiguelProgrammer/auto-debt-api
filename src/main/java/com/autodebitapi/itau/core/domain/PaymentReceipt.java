package com.autodebitapi.itau.core.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PaymentReceipt(
    String operationId,
    String debtId,
    String debitTransactionId,
    String reversalTransactionId,
    PaymentStatus status,
    BigDecimal amount,
    String message,
    OffsetDateTime createdAt
) {}
