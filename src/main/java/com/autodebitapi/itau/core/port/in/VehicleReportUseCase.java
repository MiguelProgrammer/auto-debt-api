package com.autodebitapi.itau.core.port.in;

import com.autodebitapi.itau.core.domain.ReportOption;
import com.autodebitapi.itau.core.domain.ReportRequest;
import java.time.LocalDate;
import java.util.List;

public interface VehicleReportUseCase {
    List<ReportOption> listOptions();
    ReportRequest requestPaidTaxesReport(LocalDate startDate, LocalDate endDate, String email, String phone);
    ReportRequest getReport(String reportId);
}
