# Spring WebFlux Aggregation

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-webclient-aggregation/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-webclient-aggregation/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

## Overview

A simple application that demonstrates **Spring WebFlux Aggregation** (aka **Gateway Aggregation**): it sends requests
to multiple microservices and aggregates their responses into a single response using the non-blocking
**Spring WebClient**.

## Tech Stack

| Component       | Version                                     |
|-----------------|---------------------------------------------|
| Java            | 25 (Temurin in CI)                          |
| Spring Boot     | 4.1.x                                       |
| Spring WebFlux  | Reactive web stack + `WebClient`            |
| Build tool      | Maven Wrapper (`./mvnw`)                    |
| Mock backend    | [json-server](https://github.com/typicode/json-server) via Docker Compose |
| Others          | Lombok, Spring Boot Docker Compose support  |

> **Spring Boot 4 note:** the `WebClient.Builder` auto-configuration now lives in a dedicated module, so the project
> depends on `spring-boot-starter-webclient` in addition to `spring-boot-starter-webflux`.
>
> **JDK 23+ note:** implicit annotation processing is disabled, so Lombok and the configuration processor are declared
> explicitly in `maven-compiler-plugin` → `annotationProcessorPaths`.

## Spring WebFlux Aggregation

In a microservice architecture we have N services, each with its own responsibilities & database, so data is
distributed among them. For example:

* **user-service**: responsible for user management.
* **order-service**: responsible for managing customers' orders.

Let's say the user profile page should show user information along with the user's orders. The frontend would have to
call these 2 microservices to build the page — and a dashboard page might need many more. Letting the frontend make
multiple calls is usually NOT preferred. Instead, an **aggregator** service receives the request from the frontend,
gathers the required information from multiple microservices, builds the combined response and sends it back.

### Advantages

* Reduced network calls & latency
* The aggregator acts like a facade & hides the backend complexity

**Spring WebFlux** & **Spring WebClient** are a perfect fit to build this aggregator, making these calls in a
non-blocking way to utilize system resources efficiently.

## Sample Application

The frontend requires complete product information:

* product details
* promotion details
* reviews / customer feedback

Each is managed by a different microservice (simulated by json-server), and the aggregator builds the response.

<details>
<summary>Sample data (<code>data/db.json</code>)</summary>

products-service

```json
"products": [
  { "id": 1, "description": "iphone", "category": "electronics" },
  { "id": 2, "description": "air-fryer", "category": "kitchen" },
  { "id": 3, "description": "calming pet bed", "category": "pet supplies" }
]
```

promotions-service

```json
"promotions": [
  { "id": 1, "type": "christmas sale", "discount": 12.0, "endDate": "2022-12-26" },
  { "id": 3, "type": "new year sale", "discount": 6.0, "endDate": "2022-12-31" }
]
```

ratings-service

```json
"reviews": [
  { "productId": 1, "user": "sam", "rating": 4, "comment": "it is good" },
  { "productId": 2, "user": "jake", "rating": 2, "comment": "stopped working after 2 weeks :(" }
]
```

</details>

## Getting Started

### Prerequisites

* JDK 25
* Docker (for the mock backend services)

### Run the mock backend

The `docker-compose.yaml` exposes the REST APIs on port `3000`:

```yaml
services:
  server:
    image: "clue/json-server"
    ports:
      - "3000:80"
    volumes:
      - ${PWD}/db.json:/data/db.json
```

```shell
docker compose up -d

# product 1 information
curl http://localhost:3000/products/1
# promotion details for product id = 1
curl http://localhost:3000/promotions/1
# all reviews for product id = 1
curl "http://localhost:3000/reviews?productId=1"
```

> Thanks to `spring-boot-docker-compose`, running the app also starts the compose services automatically.

### Build & run the aggregator

```shell
./mvnw clean verify          # build & run tests
./mvnw spring-boot:run       # start on http://localhost:8080
```

### Aggregated endpoint

```shell
curl http://localhost:8080/product/1
```

Returns the product, its promotion and its reviews in a single response (`404` when the product does not exist).

## CI/CD

GitHub Actions ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) runs on every push / pull request to
`main` (and manually via *workflow_dispatch*):

1. Set up **JDK 25** (Temurin) with Maven cache
2. `./mvnw -B verify`
3. Submit the Maven dependency graph to GitHub (push to `main` only) for better Dependabot alerts

## Dependabot

[`.github/dependabot.yml`](.github/dependabot.yml) checks for updates **weekly (Monday 07:00 Asia/Jakarta)** for:

| Ecosystem        | Grouping                                                         | Commit prefix   |
|------------------|------------------------------------------------------------------|-----------------|
| Maven            | `spring-boot`, `netty`, `test-dependencies`, `maven-plugins`     | `chore(deps)`   |
| GitHub Actions   | all actions in one PR                                            | `ci(deps)`      |
| Docker Compose   | —                                                                | `chore(docker)` |

Spring Boot **major** upgrades are ignored, since they usually need a manual migration.

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
