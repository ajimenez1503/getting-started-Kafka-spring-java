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


# Exercise 2:
Simple, send and receive a custom object.

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
--header 'Content-Type: application/json' \
--data-raw '{
    "text": "hola",
    "time": "2020-02-22T16:37:23z",
    "userName":"jimenez"
}'
```


# Exercise 3:
Deserialization error handeling

When there is an error, the following exeception is throw:
```
java.lang.IllegalStateException: This error handler cannot process 'SerializationException's directly; please consider configuring an 'ErrorHandlingDeserializer' in the value and/or key deserializer
	at org.springframework.kafka.listener.DefaultErrorHandler.handleOtherException(DefaultErrorHandler.java:198) ~[spring-kafka-3.0.0.jar:3.0.0]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.handleConsumerException(KafkaMessageListenerContainer.java:1950) ~[spring-kafka-3.0.0.jar:3.0.0]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1390) ~[spring-kafka-3.0.0.jar:3.0.0]
	at java.base/java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1804) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
Caused by: org.apache.kafka.common.errors.RecordDeserializationException: Error deserializing key/value for partition test-0 at offset 2. If needed, please seek past the record to continue consumption.
	at org.apache.kafka.clients.consumer.internals.Fetcher.parseRecord(Fetcher.java:1435) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.access$3400(Fetcher.java:133) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher$CompletedFetch.fetchRecords(Fetcher.java:1658) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher$CompletedFetch.access$1900(Fetcher.java:1494) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.fetchRecords(Fetcher.java:716) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.collectFetch(Fetcher.java:682) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.KafkaConsumer.pollForFetches(KafkaConsumer.java:1291) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1247) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1220) ~[kafka-clients-3.3.1.jar:na]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollConsumer(KafkaMessageListenerContainer.java:1665) ~[spring-kafka-3.0.0.jar:3.0.0]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doPoll(KafkaMessageListenerContainer.java:1640) ~[spring-kafka-3.0.0.jar:3.0.0]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1441) ~[spring-kafka-3.0.0.jar:3.0.0]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1338) ~[spring-kafka-3.0.0.jar:3.0.0]
	... 2 common frames omitted
Caused by: org.apache.kafka.common.errors.SerializationException: com.fasterxml.jackson.databind.JsonMappingException: userName is marked non-null but is null
 at [Source: (byte[])"{"text":"hola","time":1582389443.000000000,"userName":null}"; line: 1, column: 55] (through reference chain: com.example.kafka.model.Message["userName"])
	at com.example.kafka.model.MessageDeserializer.deserialize(MessageDeserializer.java:21) ~[classes/:na]
	at com.example.kafka.model.MessageDeserializer.deserialize(MessageDeserializer.java:10) ~[classes/:na]
	at org.apache.kafka.common.serialization.Deserializer.deserialize(Deserializer.java:60) ~[kafka-clients-3.3.1.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.parseRecord(Fetcher.java:1426) ~[kafka-clients-3.3.1.jar:na]
	... 14 common frames omitted
Caused by: com.fasterxml.jackson.databind.JsonMappingException: userName is marked non-null but is null
 at [Source: (byte[])"{"text":"hola","time":1582389443.000000000,"userName":null}"; line: 1, column: 55] (through reference chain: com.example.kafka.model.Message["userName"])
	at com.fasterxml.jackson.databind.JsonMappingException.from(JsonMappingException.java:276) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty._throwAsIOE(SettableBeanProperty.java:627) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty._throwAsIOE(SettableBeanProperty.java:615) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:143) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:314) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4730) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3738) ~[jackson-databind-2.14.1.jar:2.14.1]
	at com.example.kafka.model.MessageDeserializer.deserialize(MessageDeserializer.java:19) ~[classes/:na]
	... 17 common frames omitted
Caused by: java.lang.NullPointerException: userName is marked non-null but is null
	at com.example.kafka.model.Message.setUserName(Message.java:15) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor7.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
	at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:141) ~[jackson-databind-2.14.1.jar:2.14.1]
	... 23 common frames omitted
```

It is solved by handeling the exception.


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
- Terminal3: Make a request to publish a message with a deserialization error:
```
curl --location --request POST 'http://localhost:8080/kafka/publish' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text": "hola",
    "time": "2020-02-22T16:37:23z",
    "wrongField":"jimenez"
}'
```
