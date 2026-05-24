package com.autodebitapi.itau.core.domain;

public record SettlementResult(
    boolean success,
    String protocol,
    String message
) {}
