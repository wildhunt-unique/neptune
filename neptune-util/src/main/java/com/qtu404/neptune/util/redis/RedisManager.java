package com.qtu404.neptune.util.redis;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/18
 */
@Component
@Slf4j
public class RedisManager {
    private final JedisPool jedisPool;

    @Autowired
    public RedisManager(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(0);
            value = jedis.get(key);
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(0);
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    public String remove(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(0);
            jedis.del(key);
            return "1";
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 返还到连接池
     *
     * @param jedisPool 连接池
     * @param jedis     连接
     */
    private static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    public static class Util {
        public static String getKey(String... params) {
            return Joiner.on(":").join(params);
        }
    }
}
