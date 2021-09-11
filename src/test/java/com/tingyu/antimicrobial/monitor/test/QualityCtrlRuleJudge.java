package com.tingyu.antimicrobial.monitor.test;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QualityCtrlRuleJudge {

    List<BigDecimal> resultValues = new ArrayList<>();


    @Before
    public void init() {
        for (int i = 50; i < 70; i++) {
            resultValues.add(BigDecimal.valueOf(i));
        }
    }

    @Test
    public void test() {
        BigDecimal[] bigDecimals = resultValues.toArray(new BigDecimal[resultValues.size()]);
        int N = 6;
        int M = 3;
        int high = 53;
        int low = 25;

        int len = bigDecimals.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;

            //current value after N number value
            // i-N ---> i+N
            int start = i - N < 0 ? 0 : i - N;

            int end = i + N > len ? len : i + N;

            int count = 0;
            for (int j = start; j <= end; j++) {
                if (bigDecimals[j].compareTo(BigDecimal.valueOf(high)) > 0 || bigDecimals[j].compareTo(BigDecimal.valueOf(low)) < 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count >= M) {
                    flag = true;
                    break;
                }
            }
            //get value of
            if (flag) {
                System.out.println("out of ctrl value：" + bigDecimals[i]);
            } else {
                System.out.println("not out of ctrl：" + bigDecimals[i]);
            }
        }
    }
}
