package com.tingyu.kjyjcxt.exception;

import com.tingyu.kjyjcxt.common.PublicResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author essionshy
 * @Create 2021/7/16 19:55
 * @Version kjyjcxt
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public PublicResult exception(Exception e) {
        e.printStackTrace();
        return new PublicResult(900, e.getMessage(), null);
    }

}
