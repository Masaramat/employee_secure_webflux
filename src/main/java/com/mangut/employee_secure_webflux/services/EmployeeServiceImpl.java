package com.mangut.employee_secure_webflux.services;

import com.mangut.employee_secure_webflux.exceptions.EmployeeNotFoundException;
import com.mangut.employee_secure_webflux.models.Employee;
import com.mangut.employee_secure_webflux.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository repository;
    @Override
    public Flux<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Mono<Employee> save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Mono<Employee> getEmployee(Long id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee does not exist")));
    }

    @Override
    public Mono<Employee> updateEmployee(Long id, Employee employee) {
        return repository.findById(id).switchIfEmpty(Mono.error(
                new EmployeeNotFoundException("Employee not found")))
                .flatMap(existingEmployee->{
                    existingEmployee.setId(id);
                    existingEmployee.setEmail(employee.getEmail());
                    existingEmployee.setFirstName(employee.getFirstName());
                    existingEmployee.setLastName(employee.getLastName());
                    existingEmployee.setPhone(employee.getPhone());
                    existingEmployee.setDepartmentId(employee.getDepartmentId());

                return  repository.save(existingEmployee);
                });

    }

    @Override
    public Mono<Void> deleteEmployee(Long id) {
        return repository.deleteById(id).switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee Not Found")));

    }
}
