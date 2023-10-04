package com.example.burgerqueen_proj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity handleBuisnessLogicExcepttion(BusinessLogicException e){
        return new ResponseEntity(e.getExceptionCode().getMessage(), HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

}
