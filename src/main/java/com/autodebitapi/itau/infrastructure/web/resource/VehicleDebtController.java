package com.autodebitapi.itau.infrastructure.web.resource;

import com.autodebitapi.itau.core.domain.VehicleDebtType;
import com.autodebitapi.itau.core.port.in.ConsultVehicleDebtsUseCase;
import com.autodebitapi.itau.infrastructure.web.dto.ConsultDebtsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle-debts")
public class VehicleDebtController {

    private final ConsultVehicleDebtsUseCase consultVehicleDebtsUseCase;

    public VehicleDebtController(ConsultVehicleDebtsUseCase consultVehicleDebtsUseCase) {
        this.consultVehicleDebtsUseCase = consultVehicleDebtsUseCase;
    }

    @GetMapping
    public ConsultDebtsResponse consult(
            @RequestParam VehicleDebtType type,
            @RequestParam(required = false) String plate,
            @RequestParam(required = false) String renavam) {
        return new ConsultDebtsResponse(consultVehicleDebtsUseCase.consult(type, plate, renavam));
    }
}
