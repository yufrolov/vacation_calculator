package com.yufrolov.vacationcalculator.controller;

import com.yufrolov.vacationcalculator.dto.VacationPaycheckDto;
import com.yufrolov.vacationcalculator.service.VacationPaycheckService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class VacationPaycheckController {

    private final VacationPaycheckService vacationPaycheckService;

    public VacationPaycheckController(VacationPaycheckService vacationPaycheckService) {
        this.vacationPaycheckService = vacationPaycheckService;
    }

    @GetMapping("/calculate")
    public VacationPaycheckDto calculateVacationPaycheck(@RequestParam Double averageSalary, @RequestParam Integer numberVacationDays, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date startDate) {
        return vacationPaycheckService.calculateVacation(averageSalary, numberVacationDays, startDate);
    }
}