package com.hmdp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 为了使用redisson的一些特性，这些特性lettuce不具有：
本来是可以在yml中导入配置，但是存在一个问题：可能与lettuce互相覆盖！

使用的特性是：可重入锁，防止死锁！
*/

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:8008").setPassword("abc123456");
        return Redisson.create(config);
    }
}
