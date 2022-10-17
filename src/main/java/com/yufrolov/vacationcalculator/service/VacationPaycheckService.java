package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.dto.VacationPaycheckDto;
import com.yufrolov.vacationcalculator.exception.IncorrectInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class VacationPaycheckService {


    private final WorkingDaysService workingDaysService;

    private final static BigDecimal AVERAGE_NUMBER_DAYS_MONTH = BigDecimal.valueOf(29.3);

    @Autowired
    public VacationPaycheckService(WorkingDaysService workingDaysService) {
        this.workingDaysService = workingDaysService;
    }

    public VacationPaycheckDto calculateVacation(Double averageSalary, Integer numberVacationDays, Date startDate) {
        if (averageSalary == null || numberVacationDays == null || averageSalary <= 0 || numberVacationDays <= 0) {
            throw new IncorrectInputException("Некорректные параметры: averageSalary и numberVacationDays должы быть больше нуля");
        }
        return new VacationPaycheckDto(BigDecimal.valueOf(averageSalary)
                .divide(AVERAGE_NUMBER_DAYS_MONTH, 2, RoundingMode.HALF_DOWN)
                .multiply(BigDecimal.valueOf(numberVacationDays - workingDaysService.getWorkingDays(startDate, numberVacationDays)))
                .doubleValue());
    }


}
