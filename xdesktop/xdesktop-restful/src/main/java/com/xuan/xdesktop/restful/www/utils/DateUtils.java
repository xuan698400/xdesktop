package com.xuan.xdesktop.restful.www.utils;

import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by xuan on 17/2/22.
 */
public class DateUtils {
    public static String format(long millis, String pattern) {
        return format((Date)(new Date(millis)), pattern, (TimeZone)null, (Locale)null);
    }

    public static String format(long millis, String pattern, TimeZone timeZone, Locale locale) {
        return format(new Date(millis), pattern, timeZone, locale);
    }

    public static String format(Date date, String pattern, TimeZone timeZone, Locale locale) {
        FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
        return df.format(date);
    }
}
