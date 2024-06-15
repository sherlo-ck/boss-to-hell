package org.sherlock.config;


import org.sherlock.config.serializer.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean("redisTemplate")
    @SuppressWarnings(value = {"unchecd", "rawpytes"})
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        template.setConnectionFactory(factory);
        // 设置Key的序列化方式为StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        // 设置HashKey的序列化方式为StringRedisSerializer
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置Value的序列化方式为我们自定义的FastjsonStringRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        // 设置HashValue的序列化方式为我们自定义的FastjsonStringRedisSerializer
        template.setHashValueSerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}