#!/bin/bash

docker build -f ./frontend-service/Dockerfile -t frontend-service ./frontend-service
docker build -f ./processing-service/Dockerfile -t processing-service ./processing-service
docker build -f ./settings-service/Dockerfile -t settings-service ./settings-service

# make images directly available to Minikube
minikube image load frontend-service
minikube image load processing-service
minikube image load settings-service