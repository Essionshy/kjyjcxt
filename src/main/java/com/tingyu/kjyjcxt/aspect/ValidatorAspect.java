package com.tingyu.kjyjcxt.aspect;

import com.tingyu.kjyjcxt.annotation.ValidatorHandler;
import com.tingyu.kjyjcxt.validator.AbstractValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author essionshy
 * @Create 2021/5/24 22:40
 * @Version kjyjcxt
 */
@Component
@Aspect
public class ValidatorAspect {


    @Pointcut(value = "@annotation(com.tingyu.kjyjcxt.annotation.ValidatorHandler)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {

        Object obj = null;


        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(),signature.getMethod().getParameterTypes());
            //获取方法的注解
            ValidatorHandler validatorHandler = method.getAnnotation(ValidatorHandler.class);
            if(validatorHandler !=null){
                //执行验证操作
                Class<? extends AbstractValidator> validators = validatorHandler.validators();
                AbstractValidator abstractValidator = validators.newInstance();
                //获取方法参数
                Object[] args = joinPoint.getArgs();

                abstractValidator.validate(args[0]);



            }

            //前置通知
            obj = joinPoint.proceed();
            //后置通知
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //异常通知
        }


        return obj;
    }


}
