package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.service.thirdparty.IsDayOffClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkingDaysServiceTest {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Test
    void getWorkingDaysCheckResponseTest() throws ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20031229");
        var isDayOffClientMock = Mockito.mock(IsDayOffClient.class);
        Mockito.when(isDayOffClientMock.send(ArgumentMatchers.any())).thenReturn("100110010001");
        var service = new WorkingDaysService(isDayOffClientMock);
        var result = service.getWorkingDays(startDate, 2);
        assertEquals(7, result);
    }

    @Test
    void getWorkingDaysNullParamDateTest() {
        var isDayClientMock = Mockito.mock(IsDayOffClient.class);
        var service = new WorkingDaysService(isDayClientMock);
        var result = service.getWorkingDays(null, 10);
        assertEquals(10, result);

    }

}