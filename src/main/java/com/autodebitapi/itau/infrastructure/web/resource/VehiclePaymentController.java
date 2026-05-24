package com.autodebitapi.itau.infrastructure.web.resource;

import com.autodebitapi.itau.core.domain.PaymentReceipt;
import com.autodebitapi.itau.core.port.in.PayVehicleDebtUseCase;
import com.autodebitapi.itau.infrastructure.web.dto.PayDebtRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle-debt-payments")
public class VehiclePaymentController {

    private final PayVehicleDebtUseCase payVehicleDebtUseCase;

    public VehiclePaymentController(PayVehicleDebtUseCase payVehicleDebtUseCase) {
        this.payVehicleDebtUseCase = payVehicleDebtUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentReceipt pay(@Valid @RequestBody PayDebtRequest request) {
        return payVehicleDebtUseCase.pay(
            request.debtId(),
            request.plate(),
            request.renavam(),
            request.accountId(),
            request.email(),
            request.phone()
        );
    }
}
