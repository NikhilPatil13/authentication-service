# Package Structure

## Base Package

com.nikhil.authservice

## Structure

com.nikhil.authservice

├── auth
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   ├── entity
│   └── mapper
│
├── user
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   ├── entity
│   └── mapper
│
├── security
│   ├── filter
│   ├── jwt
│   └── configuration
│
├── common
│   ├── constants
│   ├── util
│   └── response
│
├── exception
│   ├── handler
│   └── custom
│
└── config

## Packaging Strategy

Package By Feature

## Benefits

* Better modularity
* Easier maintenance
* Improved feature ownership
* Easier migration to microservices
