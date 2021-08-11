package com.tingyu.kjyjcxt.util;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2021/6/28 21:52
 * @Version kjyjcxt
 */
public class DateTimeUtils {


    /**
     * 將Date类型日期转换为LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate convert(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }


    /**
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDaysBetweenBeginAndEnd(Date begin, Date end) {
        List<String> retList = new ArrayList<>();
        int beginYear = DateUtil.year(begin);
        int endYear = DateUtil.year(end);
        int beginMonth = DateUtil.month(begin) + 1;
        int endMonth = DateUtil.month(end) + 1;
        int beginDay = DateUtil.dayOfMonth(begin);
        int endDay = DateUtil.dayOfMonth(end);
        if (beginYear == endYear) {
            //compare the month value
            retList.addAll(buildOfDay(beginYear, beginMonth, beginDay, 2));
            for (int i = beginMonth + 1; i <= endMonth; i++) {
                if (i < endMonth) {
                    retList.addAll(buildOfDay(beginYear, i, 0, 3));
                } else {
                    retList.addAll(buildOfDay(beginYear, i, endDay, 1));
                }
            }
        } else {
            //qishinian
            for (int j = beginYear; j <= endYear; j++) {

                if (j == beginYear) {
                    retList.addAll(buildOfYear(j, beginMonth, beginDay, 1));
                } else if (j < endYear) {
                    retList.addAll(buildOfYear(j, -1, -1, 2));

                } else {
                    retList.addAll(buildOfYear(j, endMonth, endDay, 3));
                }
            }
        }
        return retList;
    }


    //begin year - the day end of year

    public static List<String> buildOfYear(int year, int month, int day, int type) {
        List<String> result = new ArrayList<>();
        if (type == 1) {
            for (int i = month; i <= 12; i++) {
                if (i == month) {
                    result.addAll(buildOfDay(year, i, day, 2));
                } else {
                    result.addAll(buildOfDay(year, i, -1, 3));
                }
            }
        } else if (type == 2) {
            for (int i = 1; i <= 12; i++) {
                result.addAll(buildOfDay(year, i, -1, 3));
            }
        } else if (type == 3) {
            for (int i = 1; i <= month; i++) {
                if (i < month) {
                    result.addAll(buildOfDay(year, i, -1, 3));
                } else {
                    result.addAll(buildOfDay(year, i, day, 1));
                }
            }
        } else {
            throw new RuntimeException("not support");
        }
        return result;
    }


    public static String build(int month, int day) {
        return build(month, day, "-");
    }

    public static String build(int month, int day, String sequence) {
        return StringUtils.join(new String[]{String.valueOf(month), String.valueOf(day)}, sequence);
    }

    /**
     * type [first day of month to day] 1, [day to end of month]2 ,full day of month 3
     *
     * @param year
     * @param month
     * @param day
     * @param type
     * @return
     */
    public static List<String> buildOfDay(int year, int month, int day, int type) {
        List<String> result = new ArrayList<>();

        int start = 0;
        int end = 0;
        switch (type) {
            case 1:
                start = 1;
                end = day;
                break;
            case 2:
                start = day;
                end = getDaysOfMonth(year, month);
                break;
            case 3:
                start = 1;
                end = getDaysOfMonth(year, month);
                break;
            default:
                throw new RuntimeException("the day out of normal value");
        }
        while (start <= end) {
            result.add(build(month, start));
            start++;
        }
        return result;
    }


    /**
     * @param year
     * @param month
     * @return
     */
    private static int getDaysOfMonth(int year, int month) {
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static Date parse(String text) {
        LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(zoneId).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static void main(String[] args) {

        Date begin = parse("2021-11-23");
        Date end = parse("2022-03-30");

        System.out.println(getDaysBetweenBeginAndEnd(begin, end));

        List<String> days = buildOfDay(2020, 2, 10, 2);
        System.out.println(days);


    }


}
