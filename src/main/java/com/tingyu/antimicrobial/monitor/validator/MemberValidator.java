package com.tingyu.antimicrobial.monitor.validator;

import com.tingyu.antimicrobial.monitor.entity.PatientEntity;

/**
 * @Author essionshy
 * @Create 2021/5/24 22:56
 * @Version kjyjcxt
 */
public class MemberValidator extends AbstractValidator {

    private static final int MIN_AGE = 0;

    private static final int MAX_AGE = 100;

    @Override
    protected void validateInternal(Object obj) {

        if (obj instanceof PatientEntity) {
            PatientEntity memberEntity = (PatientEntity) obj;
            //校验年龄
       /*     if (memberEntity.getAge() <= MIN_AGE || memberEntity.getAge()>=MAX_AGE){
                System.out.println("年龄不符合要求");
            }*/

        }

    }
}
