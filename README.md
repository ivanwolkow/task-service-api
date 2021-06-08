# Task service
This is a simple Spring Boot App capable of creating/persisting/retrieving entity from database.

## Build and run
As a prerequisite, the following environment variables should be set:
```
JDBC_DATABASE_URL
JDBC_DATABASE_USERNAME
JDBC_DATABASE_PASSWORD
```
Build fat jar and run it:
```bash
./mvnw clean package
java -jar target/task-service-api-1.0-SNAPSHOT.jar
```
