package com.example.restexample;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Diese Klasse wird verwendet, weil sich der Code in one() und all() wiederholt hat
 * linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
 * linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
 *
 * Die Funktion, welche einen Employee in ein EntityModel<Employee> konvertiert muss ausgelagert werden.
 * Spring HATEOS bietet hierfür das RepresentationModelAssembler Interface an.
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    /**
     * Konvertiert Employee in ein Model-based Object (EntityModel<Employee>)
     * @param employee
     * @return
     */
    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
