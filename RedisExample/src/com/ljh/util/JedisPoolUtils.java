package com.ljh.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *Jedis连接池工具类
 */

public class JedisPoolUtils {
    private static JedisPool jedisPool;

    static {

        //load properties
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //initialize config
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));

        //new pool
        jedisPool = new JedisPool(jedisPoolConfig,properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));


    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
