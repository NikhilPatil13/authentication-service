# ADR-004: Database Choice

## Status

Accepted

## Context

The Authentication Service requires a relational database capable of supporting transactional consistency, indexing, and future scalability.

## Decision

Use PostgreSQL as the primary database.

## Consequences

### Positive

* Open source
* Strong community support
* Excellent Spring Boot integration
* ACID compliance
* Advanced indexing capabilities

### Negative

* Requires database administration knowledge
* More operational complexity compared to embedded databases
