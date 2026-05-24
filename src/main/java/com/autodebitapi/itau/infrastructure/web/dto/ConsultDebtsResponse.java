package com.autodebitapi.itau.infrastructure.web.dto;

import com.autodebitapi.itau.core.domain.VehicleDebt;
import java.util.List;

public record ConsultDebtsResponse(
    List<VehicleDebt> debts
) {}
