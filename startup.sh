#!/usr/bin/env bash
sudo docker run --name own-mysql -e MYSQL_ROOT_PASSWORD=SomeRandomPass! -d mysql:5.7.19
sudo docker run --network="host" -id base-project
