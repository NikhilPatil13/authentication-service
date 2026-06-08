# High Level Architecture

## Overview

Authentication Service is a modular monolithic application responsible for authentication, authorization, and user identity management.

## Architecture Style

Modular Monolith

## Technology Stack

* Java 17
* Spring Boot
* Spring Security
* PostgreSQL
* Flyway
* Maven
* Docker

## High Level Request Flow

Client
↓
REST API
↓
Controller
↓
Service
↓
Repository
↓
PostgreSQL

## Future Integrations

* Notification Service
* Audit Service
* Multi-Tenant SaaS Platform
