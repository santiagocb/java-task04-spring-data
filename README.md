# Java Spring Data Access
The target of this exercise is to practice Spring Data Access with Java 17.

## Features
- Usage of JPA to interact with Persistence API in a booking backend.
- Usage of Hibernate
- Transactional annotations
- Entity and corresponding DBMS annotations
- Based on https://github.com/santiagocb/java-task02-spring-core

## Important learnings
- For standard methods provided by CrudRepository or JpaRepository, it's generally not necessary to write tests because those are already tested by the Spring framework itself.
- Custom methods in repositories are good candidates to be tested.
- To test a Spring Data JPA repository interface that includes custom query methods annotated with @Query, it's typically needed to write integration tests rather than unit tests. This is because the implementation of the repository interface is dynamically created at runtime by Spring Data JPA, and we want to test the actual interaction with the database to ensure that your query is correctly formed and executed.

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
![Screenshot 2024-10-16 at 11 20 06 PM](https://github.com/user-attachments/assets/1cb40afb-ad45-448d-917a-7ceabf4774e1)


## Tests Output
![Screenshot 2024-10-16 at 11 19 24 PM](https://github.com/user-attachments/assets/3c91c3a1-fbf4-4733-8357-f77f47b10cf8)
