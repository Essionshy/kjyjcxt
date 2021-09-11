package com.tingyu.antimicrobial.monitor.validator;

/**
 * @Author essionshy
 * @Create 2021/5/24 22:38
 * @Version kjyjcxt
 */
public abstract class AbstractValidator implements Validator {


    @Override
    public void validate(Object object) {
        if(object==null){
            System.out.println("参数不能为空");
        }


        validateInternal(object);
    }

    protected abstract void validateInternal(Object object);

}
