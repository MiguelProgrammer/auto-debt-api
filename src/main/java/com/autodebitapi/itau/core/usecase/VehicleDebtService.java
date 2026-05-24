package com.autodebitapi.itau.core.usecase;

import com.autodebitapi.itau.core.domain.VehicleDebt;
import com.autodebitapi.itau.core.domain.VehicleDebtType;
import com.autodebitapi.itau.core.port.in.ConsultVehicleDebtsUseCase;
import com.autodebitapi.itau.core.port.out.ProdespVehiclePort;
import java.util.List;

public class VehicleDebtService implements ConsultVehicleDebtsUseCase {

    private final ProdespVehiclePort prodespVehiclePort;

    public VehicleDebtService(ProdespVehiclePort prodespVehiclePort) {
        this.prodespVehiclePort = prodespVehiclePort;
    }

    @Override
    public List<VehicleDebt> consult(VehicleDebtType type, String plate, String renavam) {
        return prodespVehiclePort.findDebts(type, plate, renavam);
    }
}
