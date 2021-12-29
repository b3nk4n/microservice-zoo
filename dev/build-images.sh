#!/bin/bash

docker build -f ./frontend-service/Dockerfile -t frontend-service ./frontend-service
# make images directly available to Minikube
minikube image load frontend-service