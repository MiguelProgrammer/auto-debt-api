package com.autodebitapi.itau.infrastructure.config;

import com.autodebitapi.itau.core.port.in.ConsultVehicleDebtsUseCase;
import com.autodebitapi.itau.core.port.in.PayVehicleDebtUseCase;
import com.autodebitapi.itau.core.port.in.VehicleReportUseCase;
import com.autodebitapi.itau.core.port.out.BankPaymentPort;
import com.autodebitapi.itau.core.port.out.NotificationPort;
import com.autodebitapi.itau.core.port.out.PaymentRepositoryPort;
import com.autodebitapi.itau.core.port.out.ProdespVehiclePort;
import com.autodebitapi.itau.core.port.out.ReportProcessorPort;
import com.autodebitapi.itau.core.port.out.ReportRepositoryPort;
import com.autodebitapi.itau.core.usecase.VehicleDebtService;
import com.autodebitapi.itau.core.usecase.VehiclePaymentService;
import com.autodebitapi.itau.core.usecase.VehicleReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ConsultVehicleDebtsUseCase consultVehicleDebtsUseCase(ProdespVehiclePort prodespVehiclePort) {
        return new VehicleDebtService(prodespVehiclePort);
    }

    @Bean
    public PayVehicleDebtUseCase payVehicleDebtUseCase(
            ProdespVehiclePort prodespVehiclePort,
            BankPaymentPort bankPaymentPort,
            PaymentRepositoryPort paymentRepositoryPort,
            NotificationPort notificationPort) {
        return new VehiclePaymentService(prodespVehiclePort, bankPaymentPort, paymentRepositoryPort, notificationPort);
    }

    @Bean
    public VehicleReportUseCase vehicleReportUseCase(
            ReportRepositoryPort reportRepositoryPort,
            ReportProcessorPort reportProcessorPort) {
        return new VehicleReportService(reportRepositoryPort, reportProcessorPort);
    }
}
