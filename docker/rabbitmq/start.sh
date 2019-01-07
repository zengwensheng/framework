# /usr/bin/env bash

cur_dir=`pwd`

docker build -t rabbitmq:1.0 .

docker run -d -p 15672:15672 -p 5672:5672 -p 25672:25672 -p 61613:61613 -p 1883:1883  --hostname rabbitmq   --name rabbitmq  -v ${cur_dir}/data:/var/lib/rabbitmq/mnesia/rabbit@rabbitmq rabbitmq:1.0