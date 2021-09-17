package com.tingyu.antimicrobial.monitor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.tingyu.antimicrobial.monitor.dto.OfflineSampleDTO;
import com.tingyu.antimicrobial.monitor.dto.PatientDTO;
import com.tingyu.antimicrobial.monitor.entity.PatientEntity;
import com.tingyu.antimicrobial.monitor.entity.SampleEntity;
import com.tingyu.antimicrobial.monitor.lock.LockConstant;
import com.tingyu.antimicrobial.monitor.service.OfflineSampleService;
import com.tingyu.antimicrobial.monitor.service.PatientService;
import com.tingyu.antimicrobial.monitor.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午10:04
 * @Description
 */
@Slf4j
@Service
public class OfflineSampleServiceImpl implements OfflineSampleService {

    @Resource
    private PatientService patientService;
    @Resource
    private SampleService sampleService;

    @Resource
    private RedissonClient redisson;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importSave(List<OfflineSampleDTO> dtoList) {

        RLock rLock = redisson.getLock(LockConstant.PATIENT_READ_WRITE_LOCK);
        rLock.lock();
        List<PatientEntity> patientEntityList = new ArrayList<>();
        List<SampleEntity> sampleEntityList = new ArrayList<>();
        try {
            List<PatientDTO> patients = patientService.listAll();
            Map<String, String> identityCardMap = getStringStringMap(patients);

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


            patientService.saveBatch(patientEntityList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }

        sampleService.saveBatch(sampleEntityList);
    }

    private Map<String, String> getStringStringMap(List<PatientDTO> patients) {
        Map<String, String> identityCardMap = patients.stream().collect(Collectors.toMap(PatientDTO::getIdentityCard, PatientDTO::getGlobalId));
        return identityCardMap;
    }
}
