#!/bin/bash
systemctl status docker
systemctl start docker
cd ../docker/postgres
docker-compose up -d
cd ../..
mvn compile
mvn package
mvn spring-boot:run

