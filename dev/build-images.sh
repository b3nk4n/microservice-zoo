#!/bin/bash

docker build -f ./frontend-service/Dockerfile -t frontend-service ./frontend-service
docker build -f ./processing-service/Dockerfile -t processing-service ./processing-service

# make images directly available to Minikube
minikube image load frontend-service
minikube image load processing-service