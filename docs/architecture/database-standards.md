# Database Standards

## Database

PostgreSQL

## Naming Convention

Use snake_case.

### Tables

users

refresh_tokens

### Columns

id

email

password_hash

created_at

updated_at

## Primary Key Strategy

Use BIGSERIAL for primary keys.

Example:

id BIGSERIAL PRIMARY KEY

## Audit Fields

Every table should contain:

created_at

updated_at

## Migration Strategy

All schema changes must be managed through Flyway migrations.
