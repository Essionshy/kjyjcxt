package com.tingyu.antimicrobial.monitor.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午9:00
 * @Description
 */
@Data
public class OnlineSampleDTO implements Serializable {

    private String barCode;

    private String patientNo;

    @ApiModelProperty("bin")
    private String patientName;

    private String identityCard;

}
