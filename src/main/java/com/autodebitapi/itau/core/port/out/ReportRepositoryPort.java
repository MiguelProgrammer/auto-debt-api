package com.autodebitapi.itau.core.port.out;

import com.autodebitapi.itau.core.domain.ReportRequest;
import java.util.Optional;

public interface ReportRepositoryPort {
    ReportRequest save(ReportRequest report);
    Optional<ReportRequest> findById(String reportId);
}
