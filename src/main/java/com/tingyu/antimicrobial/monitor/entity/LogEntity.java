package com.tingyu.antimicrobial.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:01
 * @Version kjyjcxt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("TBLog")
@Builder
public class LogEntity {
    private String id;
    private String cbm;
    private int cmc;

}
