package com.example.restexample;

class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(Long id){
        super("Employee "+id+" does not excist");
    }
}
