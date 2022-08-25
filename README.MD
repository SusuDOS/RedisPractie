# 强化学习Redis知识点

## RedisBase

- docker启动Redis

- 五种数据类型

```bash
String
Hash
List
Set
SortedSet
```

- 命令行和GUI使用Redis

```bash
推荐：RESP
```

- 三种客户端调用

```java
# 三种java客户端调用Redis，被SpringData整合进去了，默认Lettuce,需要在yml配置参数.
# Jedis 由于线程不安全，一般使用线程池方式调用.
## 特色：调用目录与Redis命令几乎完全一致,学习成本近乎为0.

# Redisson没有详细介绍.
```

- 序列化

```bash

# 自动序列化：带类名称。浪费空间.

# 手动序列化，json风格，简洁且节约空间.

# 具体请进入内部查看！
```

