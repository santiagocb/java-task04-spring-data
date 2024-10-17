# Java Spring Data Access
The target of this exercise is to practice Spring Data Access with Java 17.

## Features
- Usage of JPA to interact with Persistence API in a booking backend.
- Usage of Hibernate
- Transactional annotations
- Entity and corresponding DBMS annotations
- Based on https://github.com/santiagocb/java-task02-spring-core

## Requirements
- Install Docker
- Download Postgres Docker image
- Install psql client

## Run the project
1. Run postgres through Docker with following command: `docker run --rm --name lil-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres`
2. Run psql command to create DB: `psql -h localhost -U postgres -f database.sql` and enter the password: `password`
3. Run Main program.
4. Run command to stop Docker execution: `docker stop lil-postgres`

## Main Output


## Tests Output