package com.mangut.employee_secure_webflux.controllers;

import com.mangut.employee_secure_webflux.models.Department;
import com.mangut.employee_secure_webflux.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping(value = "/departments")
    public Flux<Department> getAll(){
        return service.getAllDepartments();
    }

    @GetMapping(value = "/department/{id}")
    public Mono<Department> getDept(@PathVariable Long id){
        return service.getDept(id);
    }

    @PutMapping(value = "/department/{id}")
    public Mono<Department> updateDept(@PathVariable Long id, @Valid @RequestBody Department department){
        return service.updateDept(id, department);
    }

    @PostMapping(value = "/department")
    public Mono<Department> save(@RequestBody Department department){
        return service.save(department);
    }
}
