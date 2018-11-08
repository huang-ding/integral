package com.yiwenyin.integral.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author huangding
 * @description 时间处理
 * @date 2018/11/5 13:19
 */
public class DateUtil {


    /**
     * 将long类型的timestamp转为LocalDateTime
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将long类型的timestamp转为yyyy-MM-dd HH:mm:ss时间格式的字符串
     */
    public static String getDateTimeOfTimestampString(long timestamp) {
        return getDateTimeOfTimestampString(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将long类型的timestamp转为自定义的时间格式的字符串
     */
    public static String getDateTimeOfTimestampString(long timestamp, String format) {
        LocalDateTime dateTimeOfTimestamp = getDateTimeOfTimestamp(timestamp);
        return getDateTimeAsString(dateTimeOfTimestamp, format);
    }


    /**
     * 将LocalDateTime转为long类型的timestamp
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     */
    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }


}
