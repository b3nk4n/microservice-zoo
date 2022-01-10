# syntax=docker/dockerfile:1.3

FROM openjdk:17-jdk-alpine3.14

EXPOSE 8911

RUN mkdir /app

COPY build/libs/*.jar /app/settings-service.jar

ENTRYPOINT ["java","-jar","/app/settings-service.jar"]