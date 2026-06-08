# Logging Standards

## Logging Framework

SLF4J with Logback

## Log Levels

### INFO

Business events.

Examples:

* User Registration Started
* User Registration Successful
* User Login Successful

### WARN

Unexpected but recoverable situations.

Examples:

* Invalid Login Attempt
* Expired Refresh Token

### ERROR

System failures.

Examples:

* Database Connection Failure
* Unhandled Exception

## Sensitive Data Policy

Never log:

* Passwords
* JWT Tokens
* Refresh Tokens
* Secrets
* Personal Sensitive Information
