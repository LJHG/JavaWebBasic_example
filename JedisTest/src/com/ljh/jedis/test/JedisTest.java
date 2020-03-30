package com.ljh.jedis.test;

import com.ljh.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

    @Test
    public void test1(){
        //get connection
        Jedis jedis = new Jedis("localhost",6379);
        //do something
        jedis.set("username","longming");
        System.out.println(jedis.get("username"));
        //close connection
        jedis.close();
    }

    @Test
    public void test2(){
        //get connection
        Jedis jedis = new Jedis("localhost",6379);
        //do something
        jedis.hset("user","name","zhangsan");
        jedis.hset("user","password","123");
        jedis.hset("user","gender","male");

        System.out.println(jedis.hget("user","name"));
        System.out.println(jedis.hgetAll("user"));
        //close connection
        jedis.close();
    }

    @Test
    public void test3(){
        //get connection
        Jedis jedis = new Jedis("localhost",6379);
        //do something
        jedis.del("number");
        jedis.lpush("number","1","2","3");
        jedis.rpush("number","1","2","3");
        System.out.println(jedis.lrange("number",0,-1));

        //close connection
        jedis.close();
    }

    /**
    连接池测试
     */
    @Test
    public void test4(){


        //set config (not essential)
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(50);

        //get pool obj
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost", 6379);

        //get connection
        Jedis jedis = jedisPool.getResource();

        //do sth
        jedis.set("PoolTest","ok");
        System.out.println(jedis.get("PoolTest"));

        //close
        jedis.close();
    }


    /**
     * 使用工具类的连接池测试
     */
    @Test
    public void test5(){

        //get connection
        Jedis jedis = JedisPoolUtils.getJedis();

        //do sth
        jedis.set("PoolTest","the util is ok");
        System.out.println(jedis.get("PoolTest"));

        //close
        jedis.close();
    }
}
