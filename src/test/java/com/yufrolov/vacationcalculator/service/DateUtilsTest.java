package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilsTest{

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    @Test
    void getCountDateAfterTest() throws ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20221010");
        var endDate = SIMPLE_DATE_FORMAT.parse("20221016");
        var result = DateUtils.getCountDateAfter(startDate,7);
        assertEquals(endDate,result);
    }
}
