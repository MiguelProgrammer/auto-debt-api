package com.autodebitapi.itau.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReportRequestDto(
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @Email @NotBlank String email,
    @NotBlank String phone
) {}
