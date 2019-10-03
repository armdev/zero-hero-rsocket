#!/usr/bin/env bash



set -e


echo "Build the project and docker images"

mvn clean package -U -Dmaven.test.skip=true
#####mvn sonar:sonar   -Dsonar.host.url=http://192.168.99.100:9000   -Dsonar.login=a9680b5ab420fa65c3ffdf6cf2bea6a8fdd63bd9

sudo docker-compose down
echo "Start the config service first and wait for it to become available"
sudo docker-compose up -d --build 

echo  "Attach to the log output of the cluster"
#sudo docker-compose logs
sudo docker logs --follow twitter

