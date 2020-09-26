package com.example.restexample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//renders an HTTP 404
@ControllerAdvice
class EmployeeNotFoundAdvice {

    @ResponseBody //signals that advice is directly rendered into the response body
    @ExceptionHandler(EmployeeNotFoundException.class) //advice only responds if THIS exception is thrown
    @ResponseStatus(HttpStatus.NOT_FOUND) //just the statuscode
    String employeeNotFoundHandler(EmployeeNotFoundException e){
        return e.getMessage();
    }
}
