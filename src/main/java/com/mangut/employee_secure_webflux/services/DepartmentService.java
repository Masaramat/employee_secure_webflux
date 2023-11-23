package com.mangut.employee_secure_webflux.services;

import com.mangut.employee_secure_webflux.models.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    public Flux<Department> getAllDepartments();

    public Mono<Department> save(Department department);

    public Mono<Department> getDept(Long id);

    public Mono<Void> deleteDept(Long id);

    public Mono<Department> updateDept(Long id, Department department);
}
