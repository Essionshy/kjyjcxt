package com.tingyu.antimicrobial.monitor.test;

import com.tingyu.antimicrobial.monitor.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午7:33
 * @Description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisCacheTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redisson;


    @Test
    public void testRedisson() {
        RLock lock = redisson.getLock("lock");
        try {
            boolean locked = lock.tryLock(20, 30, TimeUnit.SECONDS);
            if (locked) {
                log.info("execute method..");
                TimeUnit.SECONDS.sleep(10);
            } else {
                throw new ServiceException(100, "Redisson unlock fail");
            }

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    @Test
    public void testRedisCache() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        log.info("save redis cache key hello");
        ops.set("hello", "world" + UUID.randomUUID().toString());
        log.info("query redis cache value of key hello");
        System.out.println(ops.get("hello"));
    }


}
