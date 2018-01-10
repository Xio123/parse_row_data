package com.ai.parse_row_data.util;

import java.util.Date;
import java.util.TimeZone;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 20:32 2018/1/9
 * @Modified By:
 */
public class DateUtil {

    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

    public static boolean isSameDay(Date d1, Date d2) {
        final long interval = d1.getTime() - d2.getTime();
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(d1.getTime()) == toDay(d2.getTime());
    }

    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }
}
