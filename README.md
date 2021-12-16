# EventScanner app
it is a [Spring Boot](http://projects.spring.io/spring-boot/) app which read, transform an event log file and store it in database.
This data can be access through rest end points of this application

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Building the application locally
You can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn clean install
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.cs.eventscanner.EventscannerApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run -Dspring-boot.run.arguments="<Absolute path of log file>"
```
For eg:

```shell
mvn spring-boot:run -Dspring-boot.run.arguments="C:\WorkspaceSTS\logfile.txt"
```

## Accessing application endpoint

Once the application is up, it can be access using endpoint provided in below swagger documentation

```shell
http://localhost:8080/eventscanner/swagger-ui.html

http://localhost:8080/eventscanner/v3/api-docs
```

## Accessing data base
File based database of the application can be access using below end point

```shell
http://localhost:8080/eventscanner/h2

spring.datasource.url=jdbc:h2:file:./db/events
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```
