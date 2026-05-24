package com.autodebitapi.itau.infrastructure.web.resource;

import com.autodebitapi.itau.core.domain.ReportOption;
import com.autodebitapi.itau.core.domain.ReportRequest;
import com.autodebitapi.itau.core.port.in.VehicleReportUseCase;
import com.autodebitapi.itau.infrastructure.web.dto.ReportRequestDto;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class VehicleReportController {

    private final VehicleReportUseCase vehicleReportUseCase;

    public VehicleReportController(VehicleReportUseCase vehicleReportUseCase) {
        this.vehicleReportUseCase = vehicleReportUseCase;
    }

    @GetMapping("/options")
    public List<ReportOption> options() {
        return vehicleReportUseCase.listOptions();
    }

    @PostMapping("/paid-taxes")
    public ResponseEntity<ReportRequest> requestPaidTaxesReport(@Valid @RequestBody ReportRequestDto request) {
        ReportRequest report = vehicleReportUseCase.requestPaidTaxesReport(
            request.startDate(),
            request.endDate(),
            request.email(),
            request.phone()
        );
        return ResponseEntity.accepted().body(report);
    }

    @GetMapping("/{reportId}")
    public ReportRequest getReport(@PathVariable String reportId) {
        return vehicleReportUseCase.getReport(reportId);
    }

    @GetMapping("/{reportId}/download")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String reportId) throws IOException {
        ReportRequest report = vehicleReportUseCase.getReport(reportId);
        Path file = Path.of("target", "reports", report.id() + ".xls");
        byte[] bytes = Files.readAllBytes(file);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"tributos-pagos-" + report.id() + ".xls\"")
            .body(new ByteArrayResource(bytes));
    }
}
