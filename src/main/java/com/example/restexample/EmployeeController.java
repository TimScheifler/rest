package com.example.restexample;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * CollectionModel ist ein Contailer, welcher Collections kapseln soll. Es lässt auch Links hinzufügen.
     * Es kapselt Collections von Employee-RESSOURCEN. Man holt sich mit dem Stream alle Employees
     * und transferiert sie anschließend in EntityModel<Employee> via Java 8 Streams.
     *
     * Der Return besteht dann aus einem Top-Level Self-Link und der Collection.
     *
     * Links ermöglichen es REST Services leicht zu erweitern. Bestehende bleiben beibehalten, neue
     * kommen dazu. Das ist besonders dann gut, wenn Services verlagert werden. Solange die Link-
     * Struktur die selbe bleibt, können Kunden auch weiterhin die Services finden und mit ihnen
     * interagieren.
     * @return
     */
    @GetMapping()
    CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /**
     * ResponseEntity wird benutzt um eine HTTP 201 Created Status Message zu erzeugen. Der Response
     * hat dazu noch eine Location im Header, welche mithilfe der URI aus der selbstbezogenen
     * Verknüpfung abgeleitet wird.
     * @param employee
     * @return
     */
    @PostMapping()
    ResponseEntity<?> newEmployee(@RequestBody Employee employee){

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(employee));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * EntityModel<T> is a generic container from HATEOS which includes
     * not only the data but a collection of links
     * @param id
     * @return
     */

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
        return assembler.toModel(employee);
    }

    /**
     *
     * ResponseEntity ist hier "eigentlich" fehl am Platz, weil es einen HTTP 201 Created zurück gibt,
     * was semantisch nicht so ganz stimmt. Es wird hier von Spring empfohlen, weil es mit einem
     * Location response header kommt, was ganz nützlich ist.
     * @param newEmployee
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        Employee updatedEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * ResponseEntity.noContent() gibt den Statuscode HTTP 204 No Content zurück.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
