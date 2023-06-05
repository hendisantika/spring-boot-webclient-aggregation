# Spring WebFlux Aggregation

### Overview

Let’s develop a simple application to demonstrate **Spring WebFlux Aggregation** (aka **Gateway Aggregation**) to send
requests to multiple Microservices and gather responses from them to aggregate into a single response.

spring webflux aggregation
Spring WebFlux Aggregation
Leave a Comment / Articles, Architectural Design Pattern, Architecture, Design Pattern, Java, MicroService, Reactive
Programming, Reactor, Spring, Spring WebFlux / By vIns / February 13, 2022
Overview:
In this tutorial, Let’s develop a simple application to demonstrate Spring WebFlux Aggregation (aka Gateway Aggregation)
to send requests to multiple Microservices and gather responses from them to aggregate into a single response.

### Spring WebFlux Aggregation:

In a Microservice architecture, we will have N number of services and each service has its own responsibilities &
databases.
Data would have been distributed among them. For ex:

* user-service: It is responsible for all the user management.
* order-service: It is responsible for managing customer’s orders.

Let’s say we have a requirement in which user profile page should show user information along with user’s orders.
To do this, the client/frontend might have to call these 2 microservices to build the page. If you have something like
a dashboard page, the frontend might have to call multiple microservices. Usually it is NOT a preferred approach to let
the frontend make multiple calls. Instead, we could have another microservice which acts like an aggregator whose main
responsibility is to receive the request from the frontend and gather all the required information from multiple
microservices,
build the combined response and send it to the frontend as shown here.

### Advantages:

* Reduced network calls & latency
* Aggregator acts like a facade & hides the backend complexity.

**Spring WebFlux** & **Spring WebClient** would be a perfect choice here to build this aggregator service for making
these calls in a non-blocking way to utilize the system resources efficiently.

### Sample Application:

We are going to build a sample application in which the frontend requires a complete product information.

* product details
* promotion details
* reviews / customer feedbacks.
  Let’s assume that these are all being managed by 3 different microservices. So we need an aggregator to receive the
  request and build the response.

products-service:
Add this product information.

```json
"products":[
{
"id": 1,
"description": "iphone",
"category": "electronics"
},
{
"id": 2,
"description": "air-fryer",
"category": "kitchen"
},
{
"id": 3,
"description": "calming pet bed",
"category": "pet supplies"
}
]
```

promotions-service

```json
"promotions":[
{
"id": 1,
"type": "christmas sale",
"discount": 12.0,
"endDate": "2022-12-26"
},
{
"id": 3,
"type": "new year sale",
"discount": 6.0,
"endDate": "2022-12-31"
}
]
```

ratings-service

```json
"reviews":[
{
"productId": 1,
"user": "sam",
"rating": 4,
"comment": "it is good"
},
{
"productId": 2,
"user": "jake",
"rating": 2,
"comment": "stopped working after 2 weeks :("
}
]
```

