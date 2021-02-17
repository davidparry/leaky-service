#!/bin/sh
docker build -t davidparry/leaky-service:latest src/

docker create \
--name leaky-service \
-p 8380:8380 \
davidparry/leaky-service:latest

docker start leaky-service