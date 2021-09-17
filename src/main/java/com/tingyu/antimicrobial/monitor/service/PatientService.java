package com.tingyu.antimicrobial.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.antimicrobial.monitor.dto.PatientDTO;
import com.tingyu.antimicrobial.monitor.entity.PatientEntity;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:08
 * @Version kjyjcxt
 */

public interface PatientService extends IService<PatientEntity> {
    void insert(PatientDTO dto) throws Exception;

    List<PatientDTO> listAll();
}
