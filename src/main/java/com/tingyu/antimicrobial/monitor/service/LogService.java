package com.tingyu.antimicrobial.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.antimicrobial.monitor.entity.LogEntity;

/**
 * @Author essionshy
 * @Create 2021/6/1 22:06
 * @Version kjyjcxt
 */
public interface LogService extends IService<LogEntity> {
    void saveLog();
}
