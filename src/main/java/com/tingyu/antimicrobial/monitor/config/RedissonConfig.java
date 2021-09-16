package com.tingyu.antimicrobial.monitor.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午7:01
 * @Description
 */
@Configuration
public class RedissonConfig {


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.0.103:6379");
        return Redisson.create(config);
    }


}
