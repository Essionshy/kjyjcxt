package com.tingyu.antimicrobial.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@TableName("tb_patient")
public class PatientEntity {

    @ApiModelProperty("global id")
    @TableField("global_id")
    private String globalId;
    @ApiModelProperty("name")

    @TableField("name")
    private String name;

    @TableField("country")
    private String country;

    private String nation;

    private String marriage;

    @TableField("identity_card")
    private String identityCard;
}
