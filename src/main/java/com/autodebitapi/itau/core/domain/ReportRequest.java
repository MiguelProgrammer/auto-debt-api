package com.autodebitapi.itau.core.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record ReportRequest(
    String id,
    LocalDate startDate,
    LocalDate endDate,
    String email,
    String phone,
    ReportStatus status,
    String downloadUrl,
    OffsetDateTime createdAt
) {
    public ReportRequest ready(String downloadUrl) {
        return new ReportRequest(id, startDate, endDate, email, phone, ReportStatus.READY, downloadUrl, createdAt);
    }

    public ReportRequest failed() {
        return new ReportRequest(id, startDate, endDate, email, phone, ReportStatus.FAILED, downloadUrl, createdAt);
    }
}
