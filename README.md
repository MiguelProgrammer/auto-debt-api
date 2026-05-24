# VehicleCheckAPI

[![ARCH](https://i.imgur.com/Qu91hY6.png)]()

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Architecture](https://img.shields.io/badge/Architecture-Microservices-blue.svg)](#-architecture)
[![API](https://img.shields.io/badge/API-REST-blue.svg)]()
[![Reactive](https://img.shields.io/badge/WebFlux-Reactive-success.svg)]()
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

VehicleCheckAPI is a reactive REST API designed to manage vehicle debt consultation and payment workflows, including IPVA, DPVAT, licensing fees, and traffic fines. The platform integrates mocked external services such as Prodesp and Itaú APIs for consultation, payment, reversal, and asynchronous report generation.

---

# 🚗 Main Features

## Vehicle Debt Consultation

* Consult vehicle debts by type:

  * IPVA
  * DPVAT
  * Licensing
  * Traffic fines
* Integration with mocked Prodesp API
* Reactive communication using Spring WebFlux WebClient

## Debt Payment Workflow

* Select pending debts for payment
* Process payment through mocked Itaú debit API
* Request debt clearance via mocked Prodesp API

## Automatic Reversal

If the debt clearance process fails:

* Execute automatic payment reversal
* Send SMS notification informing payment failure

## Notification System

If payment succeeds:

* Send confirmation email
* Generate payment receipt

## Tax Reports

* Generate paid tax reports using date range filters
* Support reports up to one year
* Asynchronous report generation
* Email and SMS notifications when report is ready
* Excel spreadsheet download link generation

---

# 🏗️ Final Architecture

![VehicleCheckAPI Architecture](./docs/architecture.png)

The platform is deployed on AWS EC2 using Docker Compose and follows a reactive microservices-oriented architecture with observability, external integrations, asynchronous notifications, and CI/CD automation.

## Core Components

### API Layer

* Spring Boot Reactive API
* REST Controllers
* OpenAPI / Swagger
* Validation Layer
* Resilience4j
* Spring Actuator
* Micrometer Metrics

### Infrastructure

* AWS EC2 (Ubuntu)
* Docker Compose
* Nginx Reverse Proxy + SSL
* PostgreSQL
* Redis Cache

### Observability Stack

* Prometheus
* Grafana
* Zipkin Distributed Tracing

### External Integrations

* Prodesp API (vehicle debts consultation and debt clearance)
* Itaú Banking API (debit and reversal)
* Amazon SNS (SMS notifications)
* Amazon SES (email notifications)

### CI/CD

* GitHub Actions
* Docker Build Pipeline
* Automated EC2 Deployment

---

# ⚙️ Business Flow

## Step 1 — Vehicle Debt Consultation

```text
Client → VehicleCheckAPI → Mock Prodesp API
```

The API receives the debt type and returns mocked vehicle pending debts.

---

## Step 2 — Debt Payment

```text
1. User selects debt
2. Debit payment processed
3. Debt clearance requested
4. If clearance fails:
   - reversal executed
   - SMS notification sent
5. If success:
   - receipt email sent
```

---

## Step 3 — Tax Reports

```text
1. User requests report
2. Report generated asynchronously
3. Excel file created
4. Email + SMS notification sent
5. Download link provided
```

---

# 🔌 Main Endpoints

## Debt Consultation

```http
GET /api/v1/vehicle/debts
```

### Query Params

```text
type=IPVA
type=DPVAT
type=LICENSING
type=FINE
```

---

## Debt Payment

```http
POST /api/v1/vehicle/payment
```

### Request Example

```json
{
  "vehiclePlate": "ABC1234",
  "debtIds": [1,2],
  "paymentMethod": "DEBIT"
}
```

---

## Reports

```http
POST /api/v1/reports/taxes
```

### Request Example

```json
{
  "startDate": "2025-01-01",
  "endDate": "2025-12-31"
}
```

---

# ⚡ Technologies

## Backend

* Java 21
* Spring Boot 3
* Spring WebFlux
* Spring Validation
* Spring Data JPA

## Communication

* WebClient
* REST APIs
* Reactive Streams

## Notifications

* Email Service
* SMS Gateway

## Reports

* Apache POI
* Async Processing

## Observability

* Prometheus
* Grafana
* Micrometer

## DevOps

* Docker
* Docker Compose
* GitHub Actions

---

# 🚀 Running Locally

```bash
git clone <repository>
cd vehicle-check-api
docker-compose up -d
```

---

# 📄 API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 📊 Observability

The project includes a complete observability stack running in the same EC2 environment.

## Metrics

* Payment success rate
* Payment reversal rate
* Debt consultation throughput
* External API latency
* Report generation duration
* JVM metrics
* Redis cache metrics
* PostgreSQL performance metrics

## Monitoring Stack

* Prometheus for metrics scraping
* Grafana dashboards
* Spring Boot Actuator
* Micrometer custom metrics

## Distributed Tracing

Zipkin provides request tracing across:

* Controllers
* Services
* External integrations
* Notification services
* Database operations

This enables full request visibility and troubleshooting in production environments.

---

# 🧪 Testing

## Test Types

* Unit Tests
* Integration Tests
* API Tests
* Reactive Flow Tests

## Run Tests

```bash
mvn clean test
```

---

# 🔒 Error Handling

The API includes:

* Global exception handling
* Automatic reversal strategy
* Retry policies for external integrations
* Standardized error responses

---

# 📬 Notifications

## Success Flow

* Payment receipt email

## Failure Flow

* SMS notification
* Automatic reversal

---

# 📁 Excel Reports

Generated reports include:

* Paid taxes
* Payment dates
* Vehicle information
* Debt types
* Transaction identifiers

---

# 🚀 Future Improvements

* Real Prodesp integration
* Real Itaú integration
* Pix payments
* Kafka event-driven architecture
* Kubernetes deployment
* Multi-region deployment
* Advanced fraud detection
* Distributed cache strategy
* OpenTelemetry integration
* AWS ECS migration

---

# 🚀 CI/CD Pipeline

The application deployment pipeline is fully automated using GitHub Actions.

## Pipeline Flow

```text
1. Developer pushes code to GitHub
2. GitHub Actions runs build and tests
3. Docker image is generated
4. Docker image is deployed to EC2
5. Docker Compose updates containers
```

## Pipeline Includes

* Automated tests
* Docker image build
* CI/CD deployment
* Health checks
* Observability startup

---

# ☁️ AWS Services

## AWS EC2

Hosts the complete infrastructure:

* Spring Boot API
* PostgreSQL
* Redis
* Prometheus
* Grafana
* Zipkin
* Nginx

## Amazon SNS

Responsible for:

* SMS notifications
* Payment failure alerts
* Report availability notifications

## Amazon SES

Responsible for:

* Payment confirmation emails
* Receipt delivery
* Report delivery notifications

---

# 📌 Project Goal

Provide a scalable and resilient platform for vehicle debt consultation and payment automation using reactive architecture and asynchronous processing.
