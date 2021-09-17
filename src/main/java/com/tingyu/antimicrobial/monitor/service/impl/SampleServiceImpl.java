package com.tingyu.antimicrobial.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.antimicrobial.monitor.dao.SampleMapper;
import com.tingyu.antimicrobial.monitor.entity.SampleEntity;
import com.tingyu.antimicrobial.monitor.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午10:22
 * @Description
 */
@Slf4j
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, SampleEntity> implements SampleService {
}
