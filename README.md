# getting-started-Kafka-spring-java
Getting started kafka using spring and Java


# Exercise 1:
Simple, send and receive a String.

### Running

- Terminal1: Run kafka
```
docker-compose up
```
- Terminal2: Run the service
```
cd exercise1
mvn spring-boot:run
```
- Terminal3: Make a request to publish a message in kafka
```
curl --location --request POST 'http://localhost:8080/kafka/publish' \
--header 'Content-Type: text/plain' \
--data-raw 'TEST SENDING DATA'
```

