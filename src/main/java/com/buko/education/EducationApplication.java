package com.buko.education;

import com.buko.commons.util.IPUtils;
import com.buko.commons.util.JWTUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

/**
 * @author 徐健威
 */
@MapperScan(basePackages = "com.buko.education.dao")
@SpringBootApplication(scanBasePackages = {"com.buko.education"})
public class EducationApplication extends SpringBootServletInitializer {
    private final RedisTemplate<?, ?> redisTemplate;

    @Autowired
    public EducationApplication(RedisTemplate<?, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EducationApplication.class);
    }
    private void initRedisTemplate() {
        RedisSerializer<?> serializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
    }

    @Bean
    public IPUtils ipUtil() {
        return new IPUtils();
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }


}
