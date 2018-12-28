#!/usr/bin/env bash

cur_dir=`pwd`
docker run -idt -p 6379:6379 -v ${cur_dir}/data:/data --name redis -v ${cur_dir}/redis.conf:/etc/redis/redis_ddefault.conf redis