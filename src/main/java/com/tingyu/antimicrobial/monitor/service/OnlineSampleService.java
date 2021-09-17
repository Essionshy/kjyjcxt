package com.tingyu.antimicrobial.monitor.service;

import com.tingyu.antimicrobial.monitor.dto.OnlineSampleDTO;

import java.util.List;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午9:08
 * @Description
 */
public interface OnlineSampleService {
    /**
     *
     *  @param dtoList
     */
    void signIn(List<OnlineSampleDTO> dtoList);
}
