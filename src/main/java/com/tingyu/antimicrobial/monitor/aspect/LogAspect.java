package com.tingyu.antimicrobial.monitor.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.tingyu.antimicrobial.monitor.util.RequestUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午10:38
 * @Description
 */
@Component
@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void point() {
    }


    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();

        Arrays.asList(args);

        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        if (ObjectUtil.isNotNull(annotation)) {
            String value = annotation.value();
            log.info("invoke method description :{}", value);
        }


        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
        } finally {
            long end = System.currentTimeMillis();
            log.info("request client ip :{}", RequestUtils.getIP());
            log.info("method invoke times in {} ms", end - start);
        }

        return null;
    }


}
