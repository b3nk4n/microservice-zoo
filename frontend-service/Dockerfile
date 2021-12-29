# syntax=docker/dockerfile:1.3

FROM openjdk:17-jdk-alpine3.14

EXPOSE 8080

RUN mkdir /app

COPY build/libs/*.jar /app/frontend-service.jar
COPY dev/config.yaml /app/config.yaml

ENTRYPOINT ["java","-jar","/app/frontend-service.jar", "server", "/app/config.yaml"]