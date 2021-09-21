package com.tingyu.antimicrobial.monitor.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午7:01
 * @Description
 */
@Configuration
public class RedissonConfig {


    @Resource
    RedisProperties redisProperties;


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        List<String> sentinelNodes = redisProperties.getSentinel().getNodes();
        config.useSentinelServers().addSentinelAddress(sentinelNodes.toArray(new String[sentinelNodes.size()])).setDatabase(0).setCheckSentinelsList(false).setMasterName(redisProperties.getSentinel().getMaster());
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
