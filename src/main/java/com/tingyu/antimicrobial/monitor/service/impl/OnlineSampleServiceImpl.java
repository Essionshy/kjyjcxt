package com.tingyu.antimicrobial.monitor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.tingyu.antimicrobial.monitor.dto.OnlineSampleDTO;
import com.tingyu.antimicrobial.monitor.dto.PatientDTO;
import com.tingyu.antimicrobial.monitor.entity.PatientEntity;
import com.tingyu.antimicrobial.monitor.entity.SampleEntity;
import com.tingyu.antimicrobial.monitor.lock.LockConstant;
import com.tingyu.antimicrobial.monitor.service.OnlineSampleService;
import com.tingyu.antimicrobial.monitor.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午9:09
 * @Descriion
 */
@Slf4j
@Service
public class OnlineSampleServiceImpl implements OnlineSampleService {


    @Resource
    private PatientService patientService;

    @Resource
    private RedissonClient redisson;


    @Override
    public void signIn(List<OnlineSampleDTO> dtoList) {

        RLock rLock = redisson.getLock(LockConstant.PATIENT_READ_WRITE_LOCK);

        rLock.lock();
        List<PatientEntity> patientEntityList = new ArrayList<>();
        List<SampleEntity> sampleEntityList = new ArrayList<>();
        try{
            //query all patient info list
            List<PatientDTO> patients = patientService.listAll();
            if(CollectionUtil.isEmpty(patients)){
                return;
            }
            Map<String, String> identityCardMap = patients.stream().collect(Collectors.toMap(PatientDTO::getIdentityCard, PatientDTO::getGlobalId));

            dtoList.stream().forEach(dto -> {
                String globalId;
                if (identityCardMap.containsKey(dto.getIdentityCard())) {
                    globalId = identityCardMap.get(dto.getIdentityCard());
                } else {
                    globalId = IdUtil.objectId();
                    PatientEntity entity = new PatientEntity();
                    entity.setGlobalId(globalId);
                    entity.setIdentityCard(dto.getIdentityCard());
                    patientEntityList.add(entity);
                }
                //  sample info
                SampleEntity entity = new SampleEntity();
                entity.setOrganizationCode("100001");
                entity.setGlobalId(globalId);
                //generate barCode
                entity.setBarCode(UUID.randomUUID().toString());
                sampleEntityList.add(entity);

            });

        //eecute bussiness method need long time;

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


            patientService.saveBatch(patientEntityList);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }




    }
}
