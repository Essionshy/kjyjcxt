package com.tingyu.antimicrobial.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.antimicrobial.monitor.dto.MemberDTO;
import com.tingyu.antimicrobial.monitor.entity.MemberEntity;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:08
 * @Version kjyjcxt
 */

public interface MemberService extends IService<MemberEntity> {
    void insert(MemberDTO dto) throws Exception;
}