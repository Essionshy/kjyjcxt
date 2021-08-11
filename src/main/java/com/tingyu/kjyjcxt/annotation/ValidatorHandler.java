package com.tingyu.kjyjcxt.annotation;

import com.tingyu.kjyjcxt.validator.AbstractValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author essionshy
 * @Create 2021/5/24 22:36
 * @Version kjyjcxt
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorHandler {

    Class<? extends AbstractValidator> validators();
}
