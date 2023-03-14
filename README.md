# hotels

    Avoris try-out Hotels project with Spring Boot, Maven and Java 11

## Technologies

   * Java 17
   * Spring Boot 3.0.2
   * Maven version 3.6.3
   * Mongodb
   * Kafka
   * Test Containers
   * Hexagonal Architecture
   * Docker Desktop

## Console Run

    $ mvn clean spring-boot:run

## Intellij Idea run

    1. Open a terminal and go to src/test/resources/docker, then type: docker-compose up.
       This will create the containers for kafka and mongodb.

    2. Run -> Edit Configurations -> Add New Configuration -> Application
    3. Add the project main class <<PricesApplication.java>> to the configuration and confirm, OK.
    4. Finally, select the created configuration and click run/debug.

## Postman

    The project counts with a Postman Collection meant to test the application.
    This collection is located in the next path: src/test/resources/postman/HOTELS.postman_collection.json

## Swagger 

    The REST API implemented in this application counts with an API SWAGGER Contract.