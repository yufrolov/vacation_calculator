package com.yufrolov.vacationcalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({IncorrectInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectInputExceptionHandler(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler({ThirdPartyApiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String thirdPartyApiExceptionHandler(Exception e) {
        return e.getMessage();
    }


}
