package com.autodebitapi.itau.infrastructure.client;

import com.autodebitapi.itau.core.domain.DebitResult;
import com.autodebitapi.itau.core.domain.ReversalResult;
import com.autodebitapi.itau.core.port.out.BankPaymentPort;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MockItauPaymentClient implements BankPaymentPort {

    private final WebClient webClient;

    public MockItauPaymentClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://mock.itau.com.br").build();
    }

    @Override
    public DebitResult debit(String accountId, BigDecimal amount, String description) {
        webClient.post().uri("/debitos");
        if ("FAIL-DEBIT".equalsIgnoreCase(accountId)) {
            return new DebitResult(false, null, "Falha mockada no debito Itau");
        }
        return new DebitResult(true, "DEB-" + UUID.randomUUID(), "Debito realizado");
    }

    @Override
    public ReversalResult reverse(String accountId, BigDecimal amount, String debitTransactionId, String reason) {
        webClient.post().uri("/estornos");
        return new ReversalResult(true, "EST-" + UUID.randomUUID(), "Estorno realizado");
    }
}
