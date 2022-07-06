package com.evaluation.fleet.AutoSuggest.config;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {

    private static RedisConnection redisConnection;

    public static JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.getStandaloneConfiguration().setHostName("redis");
        connectionFactory.getStandaloneConfiguration().setPort(6379);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    public RedisConnection getRedisConnection(){
        if(null == redisConnection){
            return jedisConnectionFactory().getConnection();
        }
        return redisConnection;
    }

}
