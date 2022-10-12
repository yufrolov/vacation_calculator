package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.exception.IncorrectInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VacationPaycheckServiceTest {

    @Test
    void calculateVacationValidParamsTest() {
        var service = new VacationPaycheckService();
        var result = service.calculateVacation(29.3,1);
        assertEquals(1,result.getAmount());
    }

    @Test
    void calculateNullParamTest(){
        var service = new VacationPaycheckService();
        var e = assertThrows(IncorrectInputException.class, () -> service.calculateVacation(null,null));
        assertEquals("Некорректные параметры: averageSalary и numberVacationDays должы быть больше нуля", e.getMessage());
    }

    @Test
    void calculateNegativeParamTest(){
        var service = new VacationPaycheckService();
        var e = assertThrows(IncorrectInputException.class, () -> service.calculateVacation(-3.0,-2));
        assertEquals("Некорректные параметры: averageSalary и numberVacationDays должы быть больше нуля", e.getMessage());
    }

    @Test
    void calculateRoundingTest(){
        var service = new VacationPaycheckService();
        var result = service.calculateVacation(130000.0,1);
        var stringAmount = String.valueOf(result.getAmount());
        var decimalCount = stringAmount.substring(stringAmount.indexOf(".")+1).length();
        assertEquals(2,decimalCount);

    }


}