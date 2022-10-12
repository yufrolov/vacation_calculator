package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.dto.VacationPaycheckDto;
import com.yufrolov.vacationcalculator.exception.IncorrectInputException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationPaycheckService {

    private final static BigDecimal AVERAGE_NUMBER_DAYS_MONTH = BigDecimal.valueOf(29.3);
    public VacationPaycheckDto calculateVacation(Double averageSalary, Integer numberVacationDays){
        if (averageSalary == null || numberVacationDays == null || averageSalary <= 0 || numberVacationDays <= 0){
            throw new IncorrectInputException("Некорректные параметры: averageSalary и numberVacationDays должы быть больше нуля");
        }
        return new VacationPaycheckDto(BigDecimal.valueOf(averageSalary)
                .divide(AVERAGE_NUMBER_DAYS_MONTH,2,RoundingMode.HALF_DOWN)
                .multiply(BigDecimal.valueOf(numberVacationDays))
                .doubleValue());
    }
}
