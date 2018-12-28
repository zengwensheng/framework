#!/usr/bin/env bash

cur_dir=`pwd`
docker run  --name  mysql -v ${cur_dir}/conf/config.dcd:/etc/mysql/config.d -v ${cur_dir}/data:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest