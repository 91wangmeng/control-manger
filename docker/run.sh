#!/usr/bin/env bash
echo "清理服务"
docker-compose down
sleep 5s
echo "后台管理系统启动"
docker-compose -f docker-compose.yml up -d redis mongodb rabbitmq control-manger
sleep 30s
docker-compose -f docker-compose.yml up
echo "配置中心启动"