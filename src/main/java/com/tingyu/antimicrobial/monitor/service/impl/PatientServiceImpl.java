package com.tingyu.antimicrobial.monitor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.antimicrobial.monitor.dao.PatientMapper;
import com.tingyu.antimicrobial.monitor.dto.PatientDTO;
import com.tingyu.antimicrobial.monitor.entity.PatientEntity;
import com.tingyu.antimicrobial.monitor.service.LogService;
import com.tingyu.antimicrobial.monitor.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:08
 * @Version kjyjcxt
 */
@Service
@Slf4j
public class PatientServiceImpl extends ServiceImpl<PatientMapper, PatientEntity> implements PatientService {

    @Resource
    private PatientMapper memberMapper;

    @Resource
    private LogService logService;


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(PatientDTO dto) throws Exception {

        //保存会员基本信息

        long startTime = System.currentTimeMillis();



        //保存操作日志, 如果出现异常，当前方法不捕获异常，则会导致回滚

       /* try{
            logService.saveLog();


        }catch (Exception e){
            log.error("执行日志保存操作记录出现异常，但与业务不重要，因此不必让其他执行成功的事务回滚");
        }*/
        //saveLog();
        CompletableFuture.runAsync(() -> {
            logService.saveLog();
        }).whenComplete((v, e) -> {


        });

        long endTime = System.currentTimeMillis();

        log.info("执行耗时：{}", endTime- startTime);

    }

    @Cacheable(cacheNames = {"patient"},key = "#root.methodName")
    @Override
    public List<PatientDTO> listAll() {
        List<PatientEntity> entityList = this.list();
        return CollectionUtil.isEmpty(entityList) ? Collections.EMPTY_LIST : entityList.stream().map(entity -> convert(entity)).collect(Collectors.toList());
    }


    private PatientDTO convert(PatientEntity entity) {
        PatientDTO patientDTO = new PatientDTO();
        if (ObjectUtil.isNotNull(entity)) {
            BeanUtils.copyProperties(entity, patientDTO);
        }
        return patientDTO;
    }





}
