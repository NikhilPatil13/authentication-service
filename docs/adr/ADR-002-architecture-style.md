# ADR-002: Architecture Style

## Status

Accepted

## Context

The project is being developed by a single engineer and should remain easy to develop, debug, and test.

## Decision

Use a Modular Monolith architecture.

## Consequences

### Positive

* Simpler deployment
* Easier debugging
* Easier testing
* Future microservice extraction possible

### Negative

* Requires discipline to maintain module boundaries
