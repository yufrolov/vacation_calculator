package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.exception.ThirdPartyApiException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkingDaysServiceTest {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Test
    void getWorkingDaysCheckResponseTest() throws IOException, InterruptedException, ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20031229");
        var httpClientMock = Mockito.mock(HttpClient.class);
        var httpResponseMock = Mockito.mock(HttpResponse.class);
        Mockito.when(httpClientMock.send(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(httpResponseMock);
        Mockito.when(httpResponseMock.body()).thenReturn("100110010001");
        var workingDaysService = new WorkingDaysService(httpClientMock);
        var result = workingDaysService.getWorkingDays(startDate, 2);
        assertEquals(5, result);
    }

    @Test
    void getWorkingDaysTrowExceptionTest() throws ParseException, IOException, InterruptedException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20210624");
        var e = new IOException();
        var httpClientMock = Mockito.mock(HttpClient.class);
        Mockito.when(httpClientMock.send(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(e);
        var service = new WorkingDaysService(httpClientMock);
        var eThrow = assertThrows(ThirdPartyApiException.class, () -> service.getWorkingDays(startDate, 3));
        assertEquals("Не удалось получить список выходных дней за период", eThrow.getMessage());
    }

}