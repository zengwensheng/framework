FROM rabbitmq:3.6-management-alpine

MAINTAINER zws  "2848392861@qq.com"

ADD plugins/*  /plugins

RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange
