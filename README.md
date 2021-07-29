# Kafka-Producer
Kafka Producer is a simple Spring Boot application that has Kafka configured and its only task is to send messages to Kafka. 

## Overview
The application is written using Java 11, Spring Boot and Kafka 2.0. It has one Endpoint Rest whose main task of the application is to send messages to Kafka.

### Building the application
```
mvn clean install 
```

### Building the application without tests
```
mvn clean install -DskipTests
```

### Unit tests
```
mvn test
```

The application is available on swagger:
 http://localhost:8080/swagger-ui.html