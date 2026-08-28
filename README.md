# Legacy Vaadin migration target

This is a deliberately outdated but runnable application for testing OpenRewrite and Moderne recipes.

## Baseline

- Java 11
- Spring Boot 2.7.18
- Vaadin 23.3.30
- Java EE-style `javax.*` persistence, validation, annotation, and servlet imports
- Hibernate/JPA with an in-memory H2 database
- Several intentionally old Java idioms

It is a useful target for migrations toward Java 17/21, Spring Boot 3, Jakarta EE namespaces, and Vaadin 24.

## Run

```shell
mvn spring-boot:run
```

Open http://localhost:8080.

## Test

```shell
mvn test
```

## Build an LST and run a recipe

```shell
mod build .
mod run . --recipe de.kkendzia.rewrite.Migration
```

The code is intentionally not modernized; awkward constructs are included to give cleanup and migration recipes something to change.

