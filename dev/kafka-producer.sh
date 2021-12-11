#!/bin/bash

pod=$1

if [ "$pod" == "" ]; then
  echo "POD parameter required"
  exit 1
fi

kubectl exec -it $pod -- /opt/kafka/bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic messages