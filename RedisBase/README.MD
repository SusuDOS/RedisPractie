# Redis配置

## Redis运行配置

- 建议使用Docker运行

```bash
# 简易方式运行
docker run --rm --name redis -p 6379:6379 redis:7.0.4-alpine3.16

```
```docker-compose.yml
# 相对复杂运行
version: "3.9"
services:
  redisStandalonoe:
    container_name: redisalone
    image: redis:7.0.4-alpine3.16
    volumes:
      - /root/RedisEnhencePractice/RedisConf/conf:/usr/local/etc/redis
      - /root/RedisEnhencePractice/RedisConf/data:/data
    command: redis-server /usr/local/etc/redis/redis.conf
    ports:
      - "8008:6379"
```
- 配置文件修改部分

```config
# 默认是127.0.0.1会导致只能在本地访问,修改为0.0.0.0则可以在任意IP访问，生产环境不建议设置为0.0.0.0
bind 0.0.0.0
# 守护进程，修改为yes后即可后台运行
daemonize yes 
# 密码，设置后访问Redis必须输入密码
requirepass abc123456

# 监听的端口
port 6379
# 工作目录，默认是当前目录，也就是运行redis-server时的命令，日志、持久化等文件会保存在这个目录
dir .
# 数据库数量，设置为1，代表只使用1个库，默认有16个库，编号0~15
databases 1
# 设置redis能够使用的最大内存
maxmemory 512mb
# 日志文件，默认为空，不记录日志，可以指定日志文件名
logfile "redis.log"
```

- Windows连接客户端

```bash
# 开源
https://github.com/lework/RedisDesktopManager-Windows/releases

# 其他，当前使用的是github下载的app，感觉很OK.
# RESP_app貌似付费，但是又是在上面github连接处下载安装的，未提示注册等信息。可能是同版本的不同分支....
```

- 命令行连接

```bash
# 简易方式连接
redis-cli

# 复杂带配置方式启动，命令行连接方式.
redis-cli -h 127.0.0.1 -p 8008 -a abc123456
```

## 序列化

- 自动序列化

```bash
# 1. 本地的对象要存储在redis里面，传输过去需要序列化，默认的序列化是object序列化.
# 2. 默认序列化为二进制，占用大量空间而且不方便阅读，所以使用自定义序列化工具.
# 3. key序列化为string，value序列化为json格式

# 但是取值得到的值仍然与设置的值一致，只不过redis里面存的不是如同我们设置的那样，而是object序列化后的结果；
# 获取的时候再用object反序列化回来；自定义的方式可以保证设置的样式与预期一样，序列化按照设置的序列化方式一样.
```

- 手动序列化

```bash
# 自动序列化和反序列化在redis存放格式如：
"@class":"com.heima.redis.pojo.user"
{
  "name": "虎哥",
  "age": 21
}
显然：带类名的方式占用了大量的空间，于是采用自定义序列化方式和手动序列化操作。

使用StringRedisTemplate可以解决不自定义一个序列化器，因为StringRedisTemplate默认序列化为String，

而对象中的值，不存在int，float，string的区别，都是字节码string数据类型。

```