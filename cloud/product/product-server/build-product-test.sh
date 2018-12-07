#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true -P test  -U

docker build -t registry.cn-shanghai.aliyuncs.com/michaelcloud/product:1.0 .

docker push registry.cn-shanghai.aliyuncs.com/michaelcloud/product:1.0