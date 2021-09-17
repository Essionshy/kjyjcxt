package com.tingyu.antimicrobial.monitor.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午7:01
 * @Description
 */
@Configuration
public class RedissonConfig {


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.0.103:6379");
        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        
        Map<String, CacheConfig> cacheConfigMap = new HashMap<>();
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setTTL(20 * 1000);
        cacheConfig.setMaxIdleTime(12 * 1000);
        cacheConfigMap.put("patient", cacheConfig);
        return new RedissonSpringCacheManager(redissonClient, cacheConfigMap);
    }


}
