package com.autodebitapi.itau.core.port.out;

import java.time.LocalDate;

public interface ReportProcessorPort {
    void processPaidTaxesReport(String reportId, LocalDate startDate, LocalDate endDate, String email, String phone);
}
