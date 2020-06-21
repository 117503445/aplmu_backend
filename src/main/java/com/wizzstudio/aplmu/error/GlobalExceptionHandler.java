package com.wizzstudio.aplmu.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(HttpServletRequest request, CustomException exception) throws Exception {
        return new ResponseEntity<>(exception.getMessage(), exception.getHttpStatus());
    }
}