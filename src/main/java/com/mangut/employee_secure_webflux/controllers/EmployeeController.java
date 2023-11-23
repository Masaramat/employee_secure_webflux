package com.mangut.employee_secure_webflux.controllers;

import com.mangut.employee_secure_webflux.models.Employee;
import com.mangut.employee_secure_webflux.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping(value = "/employees")
    public Flux<Employee> getAllEmployees(){
        return service.getAllEmployees();
    }

    @PostMapping(value = "/employee")
    public Mono<Employee> save(@Valid @RequestBody Employee employee){
        return service.save(employee);

    }

    @PutMapping(value = "/employee/{id}")
    public Mono<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id){
        return service.updateEmployee(id, employee);
    }

    @DeleteMapping(value = "/employee/{id}")
    public Mono<Void> deleteEmployee(@PathVariable Long id){
        return service.deleteEmployee(id);
    }

    @GetMapping(value = "/employee/{id}")
    public Mono<Employee> getEmployee(@PathVariable Long id){
        return service.getEmployee(id);
    }

}
