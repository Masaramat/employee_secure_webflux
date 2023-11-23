package com.mangut.employee_secure_webflux.services;

import com.mangut.employee_secure_webflux.models.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EmployeeService {
    public Flux<Employee> getAllEmployees();

    public Mono<Employee> save(Employee employee);

    public Mono<Employee> getEmployee(Long id);

    public Mono<Employee> updateEmployee(Long id, Employee employee);

    public Mono<Void> deleteEmployee(Long id);

}
