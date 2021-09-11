package com.tingyu.antimicrobial.monitor.util;

/**
 * @Author essionshy
 * @Create 2021/6/28 22:05
 * @Version kjyjcxt
 */
public final class StringUtils {
    public static final String HX_CONNECTOR = "-";

    public static String contact(String str1, String str2, String connector) {
        StringBuilder sb = new StringBuilder();
        sb.append(str1).append(connector).append(str2);
        return sb.toString();

    }

}
