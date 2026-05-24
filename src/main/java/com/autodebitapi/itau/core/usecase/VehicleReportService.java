package com.autodebitapi.itau.core.usecase;

import com.autodebitapi.itau.core.domain.ReportOption;
import com.autodebitapi.itau.core.domain.ReportRequest;
import com.autodebitapi.itau.core.domain.ReportStatus;
import com.autodebitapi.itau.core.domain.exception.BusinessException;
import com.autodebitapi.itau.core.domain.exception.ResourceNotFoundException;
import com.autodebitapi.itau.core.port.in.VehicleReportUseCase;
import com.autodebitapi.itau.core.port.out.ReportProcessorPort;
import com.autodebitapi.itau.core.port.out.ReportRepositoryPort;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class VehicleReportService implements VehicleReportUseCase {

    private final ReportRepositoryPort reportRepositoryPort;
    private final ReportProcessorPort reportProcessorPort;

    public VehicleReportService(ReportRepositoryPort reportRepositoryPort, ReportProcessorPort reportProcessorPort) {
        this.reportRepositoryPort = reportRepositoryPort;
        this.reportProcessorPort = reportProcessorPort;
    }

    @Override
    public List<ReportOption> listOptions() {
        return List.of(new ReportOption("TRIBUTOS_PAGOS", "Relatorio de tributos veiculares pagos"));
    }

    @Override
    public ReportRequest requestPaidTaxesReport(LocalDate startDate, LocalDate endDate, String email, String phone) {
        validateRange(startDate, endDate);
        ReportRequest report = new ReportRequest(
            UUID.randomUUID().toString(),
            startDate,
            endDate,
            email,
            phone,
            ReportStatus.PROCESSING,
            null,
            OffsetDateTime.now()
        );
        ReportRequest saved = reportRepositoryPort.save(report);
        reportProcessorPort.processPaidTaxesReport(saved.id(), startDate, endDate, email, phone);
        return saved;
    }

    @Override
    public ReportRequest getReport(String reportId) {
        return reportRepositoryPort.findById(reportId)
            .orElseThrow(() -> new ResourceNotFoundException("Relatorio nao encontrado"));
    }

    private void validateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new BusinessException("Range de datas invalido");
        }
        if (ChronoUnit.DAYS.between(startDate, endDate) > 366) {
            throw new BusinessException("Relatorio limitado ao periodo maximo de um ano");
        }
    }
}
