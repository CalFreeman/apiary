#!/bin/bash
sudo systemctl start docker
cd ../docker/postgres
docker-compose up -d
cd ../..
pwd
mvn compile
mvn package
mvn spring-boot:run

