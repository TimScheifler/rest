package com.example.restexample.employee;

import org.springframework.data.jpa.repository.JpaRepository;

//specifying the domain Type as Employee and the id type as Long.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
