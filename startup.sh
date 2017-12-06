#!/usr/bin/env bash
docker run --name own-mysql -e MYSQL_ROOT_PASSWORD=SomeRandomPass! -d mysql:5.7.19
docker run  --net=host -id base-project:1.0 
