package com.example.burgerqueen_proj.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException{

    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode=exceptionCode;
    }

    public BusinessLogicException(String test) {
    }
}
