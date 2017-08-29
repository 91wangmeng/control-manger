#!/usr/bin/env bash
echo "启动docker"
service docker start
echo "后台管理系统启动"
docker-compose -f ./docker-compose.yml up -d redis mongodb rabbitmq teamcity-server control-manger
sleep 30s
docker-compose -f ./docker-compose.yml up -d control-manger-config teamcity-agent
echo "配置中心启动"