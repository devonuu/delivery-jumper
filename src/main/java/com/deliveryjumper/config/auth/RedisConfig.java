package com.deliveryjumper.config.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-05
 * Time : 오후 1:42
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Configuration
@EnableRedisHttpSession
public class RedisConfig extends AbstractHttpSessionApplicationInitializer {

    public RedisConfig(){
        super(RedisConfig.class);
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedisTemplate<byte[], byte[]> redisTemplate(){
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        configuration.setPassword(password.isEmpty() ? RedisPassword.none() : RedisPassword.of(password));
        return new LettuceConnectionFactory(configuration);
    }
}