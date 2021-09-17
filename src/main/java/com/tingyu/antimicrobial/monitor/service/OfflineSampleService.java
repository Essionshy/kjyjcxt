package com.tingyu.antimicrobial.monitor.service;

import com.tingyu.antimicrobial.monitor.dto.OfflineSampleDTO;

import java.util.List;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午10:03
 * @Description
 */
public interface OfflineSampleService {

    void importSave(List<OfflineSampleDTO> dtoList);
}
