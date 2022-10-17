package com.yufrolov.vacationcalculator.exception;

public class ThirdPartyApiException extends RuntimeException {
    public ThirdPartyApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
