#!/usr/bin/env bash

set -e

mvn clean install -pl consumer -am -DskipTests=true
docker-compose up -d --no-deps --build consumer


