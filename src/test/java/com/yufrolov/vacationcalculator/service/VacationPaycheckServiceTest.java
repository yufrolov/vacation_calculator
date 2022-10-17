package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.exception.IncorrectInputException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VacationPaycheckServiceTest {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Test
    void calculateVacationValidParamsTest() throws ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20210624");
        var workingDaysServiceMock = Mockito.mock(WorkingDaysService.class);
        Mockito.when(workingDaysServiceMock.getWorkingDays(ArgumentMatchers.eq(startDate), ArgumentMatchers.eq(1))).thenReturn(0);
        var service = new VacationPaycheckService(workingDaysServiceMock);
        var result = service.calculateVacation(29.3, 1, startDate);
        assertEquals(1, result.getAmount());
    }

    @Test
    void calculateNullParamTest() throws ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20031229");
        var workingDaysServiceMock = Mockito.mock(WorkingDaysService.class);
        Mockito.when(workingDaysServiceMock.getWorkingDays(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1);
        var service = new VacationPaycheckService(workingDaysServiceMock);
        var e = assertThrows(IncorrectInputException.class, () -> service.calculateVacation(null, null, startDate));
        assertEquals("Некорректные параметры: averageSalary и numberVacationDays должы быть больше нуля", e.getMessage());
    }

    @Test
    void calculateNegativeParamTest() throws ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20031229");
        var workingDaysServiceMock = Mockito.mock(WorkingDaysService.class);
        Mockito.when(workingDaysServiceMock.getWorkingDays(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1);
        var service = new VacationPaycheckService(workingDaysServiceMock);
        var e = assertThrows(IncorrectInputException.class, () -> service.calculateVacation(-3.0, -2, startDate));
        assertEquals("Некорректные параметры: averageSalary и numberVacationDays должы быть больше нуля", e.getMessage());
    }

    @Test
    void calculateRoundingTest() throws ParseException {
        var startDate = SIMPLE_DATE_FORMAT.parse("20210624");
        var workingDaysServiceMock = Mockito.mock(WorkingDaysService.class);
        Mockito.when(workingDaysServiceMock.getWorkingDays(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1);
        var service = new VacationPaycheckService(workingDaysServiceMock);
        var result = service.calculateVacation(130000.0, 2, startDate);
        var stringAmount = String.valueOf(result.getAmount());
        var decimalCount = stringAmount.substring(stringAmount.indexOf(".") + 1).length();
        assertEquals(2, decimalCount);

    }


}