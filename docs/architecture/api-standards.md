# API Standards

## API Versioning

All endpoints must follow:

/api/v1/*

Examples:

POST /api/v1/auth/register

POST /api/v1/auth/login

GET /api/v1/users/me

PUT /api/v1/users/me

## Success Response Structure

{
"success": true,
"message": "Operation completed successfully",
"data": {},
"timestamp": "ISO-8601"
}

## Error Response Structure

{
"success": false,
"message": "Operation failed",
"errors": [],
"timestamp": "ISO-8601"
}

## HTTP Status Codes

200 OK

201 Created

400 Bad Request

401 Unauthorized

403 Forbidden

404 Not Found

409 Conflict

500 Internal Server Error
