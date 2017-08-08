#!/usr/bin/env bash
echo "后台管理系统启动"
docker-compose -f docker-compose.yml up -d redis mongodb rabbitmq control-manger
sleep 30s
docker-compose -f docker-compose.yml up -d control-manger-config
echo "配置中心启动"