package com.wallet.appn.component.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handle(){
        return new ResponseEntity<>("Exception occured !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
