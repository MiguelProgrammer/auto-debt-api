package com.autodebitapi.itau.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PayDebtRequest(
    @NotBlank String debtId,
    @NotBlank String plate,
    @NotBlank String renavam,
    @NotBlank String accountId,
    @Email @NotBlank String email,
    @NotBlank String phone
) {}
