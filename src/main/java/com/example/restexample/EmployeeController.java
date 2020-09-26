package com.example.restexample;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    List<Employee> all(){
        return repository.findAll();
    }

    @PostMapping()
    Employee newEmployee(@RequestBody Employee employee){
        return repository.save(employee);
    }
    // EntityModel<T> is a generic container from HATEOS which includes
    // not only the data but a collection of links
    @GetMapping("/{id}")
    EntityModel<Employee> one(@PathVariable Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        /**
         * linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel()
         * asks that Spring HATEOAS build a link to the EmployeeController 's one() method,
         * and flag it as a self link.
         *
         * linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
         * asks Spring HATEOAS to build a link to the aggregate root, all(), and call it "employees".
         */
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    @PutMapping("/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setId(newEmployee.getId());
            return repository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
}
