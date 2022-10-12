package com.yufrolov.vacationcalculator.dto;

public class VacationPaycheckDto {

    private final Double amount;

    public VacationPaycheckDto(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
