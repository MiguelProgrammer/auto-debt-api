package com.autodebitapi.itau.core.domain;

public record ReversalResult(
    boolean success,
    String transactionId,
    String message
) {}
