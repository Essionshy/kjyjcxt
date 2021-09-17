package com.tingyu.antimicrobial.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午9:56
 * @Description
 */
@TableName(value = "tb_sample")
@Data
public class SampleEntity implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String organizationCode;

    private String globalId;

    private String barCode;


}
