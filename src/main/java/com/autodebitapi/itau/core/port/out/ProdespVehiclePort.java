package com.autodebitapi.itau.core.port.out;

import com.autodebitapi.itau.core.domain.SettlementResult;
import com.autodebitapi.itau.core.domain.VehicleDebt;
import com.autodebitapi.itau.core.domain.VehicleDebtType;
import java.util.List;

public interface ProdespVehiclePort {
    List<VehicleDebt> findDebts(VehicleDebtType type, String plate, String renavam);
    VehicleDebt findDebtById(String debtId, String plate, String renavam);
    SettlementResult settleDebt(VehicleDebt debt);
}
