package com.yufrolov.vacationcalculator.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getDateAfterDays(Date startDate, Integer totalDays) {
        var calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, totalDays-1);
        return calendar.getTime();

    }
}
