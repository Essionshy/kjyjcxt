package com.tingyu.antimicrobial.monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author essionshy
 * @Create 2021/6/1 22:10
 * @Version kjyjcxt
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class PatientDTO implements Serializable {

    private String globalId;

    private String name;

    private Integer age;


    private String identityCard;



}
