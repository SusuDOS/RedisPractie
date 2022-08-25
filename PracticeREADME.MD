# Redis实用系列

功能：类似于小红书，大众点评等功能!

## 配置启动容器

```bash
# 配置需要启动的容器
# mysql+redis+nginx

# 数据库数据导入
mysql -h 127.0.0.1 -uroot -p
source ../MySqlConf/hmdp.sql

# 在8.03版本中出现出错，导入秒杀表出错，经查询资料：
# https://stackoverflow.com/questions/36882149/error-1067-42000-invalid-default-value-for-created-at

# 检查sql_mode
show variables like 'sql_mode';

# 删除 sql_mode " NO_ZERO_IN_DATE,NO_ZERO_DATE " 使其工作,这是mysql新版本中默认的sql_mode.
set global sql_mode = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

# 退出sql，重新进去sql可以导入数据.
```

## 利用redis的stream实现消息队列

```bash
# 需要手动添加队列
## 创建redis stream队列.
## g1从stream.orders中从0开始读取，若没有g1，则自动创建.
## 自动创建参数为：mkstream
xgroup create stream.orders g1 0 mkstream
```

## 短信登陆

实现通过短信验证码登录到账号，进入到app界面进行使用。

```bash
# 验证码请求
http://localhost:18080/api/user/code?phone=12345678001

# 登陆请求
http://localhost:18080/api/user/login

# 登录校验
http://localhost:18080/api/user/me
```

## 