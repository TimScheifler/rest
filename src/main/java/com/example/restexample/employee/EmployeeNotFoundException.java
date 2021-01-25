package com.example.restexample.employee;

class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(Long id){
        super("Employee "+id+" does not excist");
    }
}
