package com.buko.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @author 徐健威
 */
@Configuration
public class CacheConfig {

    private RedisConnectionFactory connectionFactory = null;

    public CacheConfig(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean(name = "redisCacheManager")
    public RedisCacheManager initRedisCacheManager() {
        // redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        // 启动redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置jdk序列化器
        config = config.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer())
        );
        // 禁用前缀
        config = config.disableKeyPrefix();
        // 设置10 min 超时
        config = config.entryTtl(Duration.ofMinutes(10));
        // 创建缓存管理器
        return new RedisCacheManager(writer, config);
    }
}
