package com.bmi.bookmarkitapi.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private DateTimeUtils() {}

    /**
     * Returns a string representation of this LocalDateTime value with format 'yyyy-MM-dd'.
     */
    public static String toISOLocalDateString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns a string representation of this LocalDateTime value with format 'yyyy-MM-dd HH:mm:ss'.
     */
    public static String toISOLocalDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Returns a string representation of this LocalDateTime value with format 'MM-dd'.
     */
    public static String toStringWithMonthAndDay(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MM-dd"));
    }
}
