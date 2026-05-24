package com.autodebitapi.itau.infrastructure.client;

import com.autodebitapi.itau.core.domain.SettlementResult;
import com.autodebitapi.itau.core.domain.VehicleDebt;
import com.autodebitapi.itau.core.domain.VehicleDebtType;
import com.autodebitapi.itau.core.domain.exception.ResourceNotFoundException;
import com.autodebitapi.itau.core.port.out.ProdespVehiclePort;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MockProdespVehicleClient implements ProdespVehiclePort {

    private final WebClient webClient;

    public MockProdespVehicleClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://mock.prodesp.sp.gov.br").build();
    }

    @Override
    public List<VehicleDebt> findDebts(VehicleDebtType type, String plate, String renavam) {
        webClient.get().uri("/veiculos/debitos?tipo={type}", type);
        return mockDebts(plate, renavam).stream()
            .filter(debt -> debt.type() == type)
            .toList();
    }

    @Override
    public VehicleDebt findDebtById(String debtId, String plate, String renavam) {
        return mockDebts(plate, renavam).stream()
            .filter(debt -> debt.id().equalsIgnoreCase(debtId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pendencia veicular nao encontrada"));
    }

    @Override
    public SettlementResult settleDebt(VehicleDebt debt) {
        webClient.post().uri("/veiculos/debitos/{id}/baixa", debt.id());
        if (debt.id().endsWith("FAIL-BAIXA")) {
            return new SettlementResult(false, null, "Falha mockada na baixa Prodesp");
        }
        return new SettlementResult(true, "PRODESP-" + debt.id(), "Baixa realizada com sucesso");
    }

    private List<VehicleDebt> mockDebts(String plate, String renavam) {
        String normalizedPlate = plate == null || plate.isBlank() ? "ABC1D23" : plate.toUpperCase();
        String normalizedRenavam = renavam == null || renavam.isBlank() ? "12345678901" : renavam;
        return List.of(
            new VehicleDebt("IPVA-2026-001", normalizedPlate, normalizedRenavam, VehicleDebtType.IPVA, "IPVA exercicio 2026", new BigDecimal("985.40"), LocalDate.now().plusDays(20)),
            new VehicleDebt("DPVAT-2026-001", normalizedPlate, normalizedRenavam, VehicleDebtType.DPVAT, "DPVAT exercicio 2026", new BigDecimal("42.35"), LocalDate.now().plusDays(30)),
            new VehicleDebt("LIC-2026-001", normalizedPlate, normalizedRenavam, VehicleDebtType.LICENCIAMENTO, "Licenciamento anual 2026", new BigDecimal("160.22"), LocalDate.now().plusDays(40)),
            new VehicleDebt("MULTA-2026-001", normalizedPlate, normalizedRenavam, VehicleDebtType.MULTAS, "Multa por excesso de velocidade", new BigDecimal("293.47"), LocalDate.now().minusDays(5)),
            new VehicleDebt("MULTA-2026-FAIL-BAIXA", normalizedPlate, normalizedRenavam, VehicleDebtType.MULTAS, "Multa com falha mockada de baixa", new BigDecimal("120.00"), LocalDate.now().plusDays(10))
        );
    }
}
