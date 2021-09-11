package com.tingyu.antimicrobial.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.antimicrobial.monitor.service.LogService;
import com.tingyu.antimicrobial.monitor.dao.LogMapper;
import com.tingyu.antimicrobial.monitor.entity.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author essionshy
 * @Create 2021/6/1 22:06
 * @Version kjyjcxt
 */
@Service
@Slf4j
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveLog() {
        LogEntity.LogEntityBuilder builder = LogEntity.builder();
        LogEntity entity = builder.id(UUID.randomUUID().toString()).cbm("1000")
                .cmc(100).build();
        boolean success = this.save(entity);
        if (success) {
            log.info("保存操作日志成功");
        } else {
            log.info("保存操作日志失败");
        }
        int i = 10 / 0;
    }
}
