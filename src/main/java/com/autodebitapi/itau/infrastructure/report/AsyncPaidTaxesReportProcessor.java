package com.autodebitapi.itau.infrastructure.report;

import com.autodebitapi.itau.core.domain.PaidTax;
import com.autodebitapi.itau.core.domain.ReportRequest;
import com.autodebitapi.itau.core.port.out.NotificationPort;
import com.autodebitapi.itau.core.port.out.PaymentRepositoryPort;
import com.autodebitapi.itau.core.port.out.ReportProcessorPort;
import com.autodebitapi.itau.core.port.out.ReportRepositoryPort;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncPaidTaxesReportProcessor implements ReportProcessorPort {

    private final PaymentRepositoryPort paymentRepositoryPort;
    private final ReportRepositoryPort reportRepositoryPort;
    private final NotificationPort notificationPort;

    public AsyncPaidTaxesReportProcessor(
            PaymentRepositoryPort paymentRepositoryPort,
            ReportRepositoryPort reportRepositoryPort,
            NotificationPort notificationPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.reportRepositoryPort = reportRepositoryPort;
        this.notificationPort = notificationPort;
    }

    @Override
    @Async
    public void processPaidTaxesReport(String reportId, LocalDate startDate, LocalDate endDate, String email, String phone) {
        reportRepositoryPort.findById(reportId).ifPresent(report -> {
            try {
                List<PaidTax> paidTaxes = paymentRepositoryPort.findPaidTaxes(startDate, endDate);
                String downloadUrl = writeExcelCompatibleReport(reportId, paidTaxes);
                ReportRequest ready = report.ready(downloadUrl);
                reportRepositoryPort.save(ready);
                notificationPort.sendEmail(email, "Relatorio de tributos pronto", "Download: " + downloadUrl);
                notificationPort.sendSms(phone, "Relatorio de tributos pronto: " + downloadUrl);
            } catch (Exception e) {
                reportRepositoryPort.save(report.failed());
                notificationPort.sendSms(phone, "Erro ao gerar relatorio de tributos: " + reportId);
            }
        });
    }

    private String writeExcelCompatibleReport(String reportId, List<PaidTax> paidTaxes) throws IOException {
        Path directory = Path.of("target", "reports");
        Files.createDirectories(directory);
        Path file = directory.resolve(reportId + ".xls");
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr><th>Operacao</th><th>Debito</th><th>Placa</th><th>Tipo</th><th>Valor</th><th>Pago em</th></tr>");
        for (PaidTax tax : paidTaxes) {
            html.append("<tr>")
                .append("<td>").append(tax.operationId()).append("</td>")
                .append("<td>").append(tax.debtId()).append("</td>")
                .append("<td>").append(tax.plate()).append("</td>")
                .append("<td>").append(tax.type()).append("</td>")
                .append("<td>").append(tax.amount()).append("</td>")
                .append("<td>").append(tax.paidAt()).append("</td>")
                .append("</tr>");
        }
        html.append("</table>");
        Files.writeString(file, html.toString(), StandardCharsets.UTF_8);
        return "/api/v1/reports/" + reportId + "/download";
    }
}
