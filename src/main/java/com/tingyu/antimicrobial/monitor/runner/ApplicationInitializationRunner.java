package com.tingyu.antimicrobial.monitor.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author
 * @CreatDate 2021/9/19 下午8:21
 * @Description
 */
@Slf4j
@Component
public class ApplicationInitializationRunner implements ApplicationRunner {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("system init start ...");
        stringRedisTemplate.opsForValue().set("username", "essionshy");
        log.info("system init end...");
    }
}
