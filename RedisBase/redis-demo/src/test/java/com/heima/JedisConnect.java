package com.heima;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;

public class JedisConnect {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        jedis = new Jedis("127.0.0.1", 8008);
        jedis.auth("abc123456");
    }

    @Test
    void testString() {
        String result = jedis.set("name", "虎哥");
        System.out.println("Result:" + result);

        String name = jedis.get("name");
        System.out.println("name:" + name);
    }

    @Test
    void testHash() {
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "18");
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println("user:1" + map);
    }
}
