package com.autodebitapi.itau.e2e;

import com.autodebitapi.itau.config.BaseIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Vehicle debt API E2E tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleDebtApiE2ETest extends BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldConsultVehicleDebtsByType() {
        given()
            .queryParam("type", "IPVA")
            .queryParam("plate", "ABC1D23")
            .queryParam("renavam", "12345678901")
        .when()
            .get("/api/v1/vehicle-debts")
        .then()
            .statusCode(200)
            .body("debts.size()", greaterThanOrEqualTo(1))
            .body("debts[0].type", equalTo("IPVA"))
            .body("debts[0].amount", notNullValue());
    }

    @Test
    void shouldPayVehicleDebtSuccessfully() {
        given()
            .contentType(ContentType.JSON)
            .body(paymentRequest("IPVA-2026-001", "ACC-123"))
        .when()
            .post("/api/v1/vehicle-debt-payments")
        .then()
            .statusCode(201)
            .body("status", equalTo("PAID"))
            .body("debitTransactionId", notNullValue())
            .body("operationId", notNullValue());
    }

    @Test
    void shouldReverseDebitWhenProdespSettlementFails() {
        given()
            .contentType(ContentType.JSON)
            .body(paymentRequest("MULTA-2026-FAIL-BAIXA", "ACC-123"))
        .when()
            .post("/api/v1/vehicle-debt-payments")
        .then()
            .statusCode(201)
            .body("status", equalTo("REVERSED"))
            .body("reversalTransactionId", notNullValue());
    }

    @Test
    void shouldListReportOptionsAndCreateAsyncReport() throws InterruptedException {
        given()
        .when()
            .get("/api/v1/reports/options")
        .then()
            .statusCode(200)
            .body("code", hasItem("TRIBUTOS_PAGOS"));

        given()
            .contentType(ContentType.JSON)
            .body(paymentRequest("DPVAT-2026-001", "ACC-123"))
        .when()
            .post("/api/v1/vehicle-debt-payments")
        .then()
            .statusCode(201)
            .body("status", equalTo("PAID"));

        String reportId = given()
            .contentType(ContentType.JSON)
            .body(Map.of(
                "startDate", LocalDate.now().minusDays(1).toString(),
                "endDate", LocalDate.now().plusDays(1).toString(),
                "email", "driver@example.com",
                "phone", "+5511999999999"
            ))
        .when()
            .post("/api/v1/reports/paid-taxes")
        .then()
            .statusCode(202)
            .body("status", equalTo("PROCESSING"))
            .body("id", notNullValue())
            .extract()
            .path("id");

        waitUntilReportIsReady(reportId);

        given()
        .when()
            .get("/api/v1/reports/{reportId}/download", reportId)
        .then()
            .statusCode(200);
    }

    private Map<String, String> paymentRequest(String debtId, String accountId) {
        return Map.of(
            "debtId", debtId,
            "plate", "ABC1D23",
            "renavam", "12345678901",
            "accountId", accountId,
            "email", "driver@example.com",
            "phone", "+5511999999999"
        );
    }

    private void waitUntilReportIsReady(String reportId) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            String status = given()
            .when()
                .get("/api/v1/reports/{reportId}", reportId)
            .then()
                .statusCode(200)
                .extract()
                .path("status");
            if ("READY".equals(status)) {
                return;
            }
            Thread.sleep(200);
        }
        throw new AssertionError("Report was not ready in time");
    }
}
