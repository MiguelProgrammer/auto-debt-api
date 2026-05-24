package com.autodebitapi.itau.infrastructure.repository;

import com.autodebitapi.itau.core.domain.ReportRequest;
import com.autodebitapi.itau.core.port.out.ReportRepositoryPort;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class InMemoryReportRepository implements ReportRepositoryPort {

    private final Map<String, ReportRequest> reports = new ConcurrentHashMap<>();

    @Override
    public ReportRequest save(ReportRequest report) {
        reports.put(report.id(), report);
        return report;
    }

    @Override
    public Optional<ReportRequest> findById(String reportId) {
        return Optional.ofNullable(reports.get(reportId));
    }
}
