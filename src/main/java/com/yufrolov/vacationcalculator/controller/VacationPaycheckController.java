package com.yufrolov.vacationcalculator.controller;

import com.yufrolov.vacationcalculator.dto.VacationPaycheckDto;
import com.yufrolov.vacationcalculator.service.VacationPaycheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPaycheckController {

    private final VacationPaycheckService vacationPaycheckService;

    @Autowired
    public VacationPaycheckController(VacationPaycheckService vacationPaycheckService) {
        this.vacationPaycheckService = vacationPaycheckService;
    }

    @GetMapping("/calculate")
    public VacationPaycheckDto calculateVacationPaycheck(@RequestParam double averageSalary, @RequestParam int numberVacationDays){
        return vacationPaycheckService.calculateVacation(averageSalary,numberVacationDays);
    }

}
