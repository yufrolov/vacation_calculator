package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.dto.VacationPaycheckDto;
import org.springframework.stereotype.Service;

@Service
public class VacationPaycheckService {

    public VacationPaycheckDto calculateVacation(double averageSalary, int numberVacationDays){
        return new VacationPaycheckDto(averageSalary / 29.3 * numberVacationDays);
    }
}
