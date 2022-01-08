# syntax=docker/dockerfile:1.3

FROM openjdk:17-jdk-alpine3.14

EXPOSE 8901

RUN mkdir /app

COPY build/libs/*.jar /app/processing-service.jar
COPY dev/config.yaml /app/config.yaml

ENTRYPOINT ["java","-jar","/app/processing-service.jar", "server", "/app/config.yaml"]