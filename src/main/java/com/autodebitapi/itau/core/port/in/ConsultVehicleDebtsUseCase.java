package com.autodebitapi.itau.core.port.in;

import com.autodebitapi.itau.core.domain.VehicleDebt;
import com.autodebitapi.itau.core.domain.VehicleDebtType;
import java.util.List;

public interface ConsultVehicleDebtsUseCase {
    List<VehicleDebt> consult(VehicleDebtType type, String plate, String renavam);
}
