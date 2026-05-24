package com.autodebitapi.itau.core.domain;

public record DebitResult(
    boolean success,
    String transactionId,
    String message
) {}
