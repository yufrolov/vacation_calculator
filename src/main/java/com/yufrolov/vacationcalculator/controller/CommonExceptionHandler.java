package com.yufrolov.vacationcalculator.controller;

import com.yufrolov.vacationcalculator.exception.IncorrectInputException;
import com.yufrolov.vacationcalculator.exception.ThirdPartyApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({IncorrectInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectInputExceptionHandler(IncorrectInputException e) {
        return e.getMessage();
    }

    @ExceptionHandler({ThirdPartyApiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String thirdPartyApiExceptionHandler(ThirdPartyApiException e) {
        return e.getMessage();
    }


}
