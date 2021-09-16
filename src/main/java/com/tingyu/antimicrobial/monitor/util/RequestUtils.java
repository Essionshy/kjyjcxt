package com.tingyu.antimicrobial.monitor.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午10:50
 * @Description
 */
public class RequestUtils {

    private RequestUtils() {
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    public static String getIP() {
        return getRequest().getRemoteAddr();
    }

}
