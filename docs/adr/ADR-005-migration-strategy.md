# ADR-005: Database Migration Strategy

## Status

Accepted

## Context

Database schema changes must be repeatable, version-controlled, and deployable across multiple environments.

## Decision

Use Flyway for database migrations.

## Consequences

### Positive

* Version-controlled schema changes
* Repeatable deployments
* Environment consistency
* Easy rollback planning

### Negative

* Requires migration discipline
* Incorrect migrations can impact deployments
