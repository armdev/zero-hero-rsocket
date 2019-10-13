#!/usr/bin/env bash

set -e

sudo mvn clean install -pl consumer -am -DskipTests=true
sudo docker-compose up -d --no-deps --build consumer


