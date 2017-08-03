# 管控后台管理系统
## 引言
> 随着公司业务的发展，管控系统的数量也在日益增加，而管控系统依赖的各种功能却缺乏一个统一后台管理系统。本系统的作用价值在于为管控平台提供统一的消息、日志、缓存以及配置文件管理功能。

## 系统功能模块划分

### 缓存模块（control-manger-cache）
> 本模块作用于提供统一的缓存功能，如：缓存查看，缓存手动删除，缓存过期时间设置等功能。缓存使用redis作为缓存数据库，将所有数据根据景区进行划分。

### 公共基础模块（control-manger-common）
> 本模块主要作用于提供管控系统一些必要的公共基础功能，如一些常用工具类缓存工具类、http工具类、日志工具类、消息工具类等等，管控通过依赖引入。

### 日志模块（control-manger-log）
> 本模块主要作用于提供各景区管控系统调用日志情况，方便在开发过程从方便快速有效的查找系统调用日志。

### 消息模块（control-manger-message）
> 本模块主要作用于提供管控系统消息服务，以及消息推送记录。


### 任务模块（control-manger-task）
> 本模块主要作用于提供管控系统定时任务注册和管理。

### 后台页面展示(control-manger-web)
> 本模块主要做用户提供页面交互展示功能。

## 安装流程
### 安装Docker
查看内核版本
>uname -r

更新yum包
>yum update


添加yum仓库
>sudo tee /etc/yum.repos.d/docker.repo <<-'EOF'

> [dockerrepo]<br/>
name=Docker Repository <br/>
baseurl=https://yum.dockerproject.org/repo/main/centos/7/<br/>
enabled=1<br/>
gpgcheck=1<br/>
gpgkey=https://yum.dockerproject.org/gpg<br/>
> EOF

安装Docker
>yum install docker-engine

启动Docker
>service docker start

使用Docker国内镜像
> curl -sSL https://get.daocloud.io/daotools/set_mirror.sh | sh -s http://fe8a7d6e.m.daocloud.io

构建docker镜像
> 通过maven插件build构建镜像,注意设置好DOCKER_HOST环境变量.
>docker pull rabbitmq:3.6.10-management<br/>
 docker run -d --hostname my-rabbit  -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin RABBITMQ_DEFAULT_VHOST=control_manger rabbitmq:3.6.10-management<br/>
 docker pull rabbitmq<br/>
 docker run -d -p 6379:6379 redis redis-server<br/>
 docker pull mongo<br/>
 docker run -d -p 27017:27017 mongodb mongodb-server<br/>


## 踩到的坑
> * 由于common依赖没控制好,导致config client引入common时同时引入了config server的依赖,导致clien的配置无法生效
> * docker中运行eurekadocker run -p 8081:8081 -i --net=host control-manger 加上-i --ner=host 才能在config客户端获取配置服务器的地址时不会获取到容器id
> * mongodb服务器默认采用GMT时间