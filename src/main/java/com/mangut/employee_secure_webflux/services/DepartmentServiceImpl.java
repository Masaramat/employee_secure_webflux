package com.mangut.employee_secure_webflux.services;

import com.mangut.employee_secure_webflux.exceptions.DepartmentNotFoundException;
import com.mangut.employee_secure_webflux.models.Department;
import com.mangut.employee_secure_webflux.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository repository;
    @Override
    public Flux<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Mono<Department> save(Department department) {
        return repository.save(department);
    }

    @Override
    public Mono<Department> getDept(Long id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new DepartmentNotFoundException("Department not found")));
    }

    @Override
    public Mono<Void> deleteDept(Long id) {
        return repository.deleteById(id).switchIfEmpty(Mono.error(new DepartmentNotFoundException("Department not found")));
    }

    @Override
    public Mono<Department> updateDept(Long id, Department department) {
        return repository.findById(id).switchIfEmpty(Mono.error(new DepartmentNotFoundException("DepartmentNot found")))
                .flatMap(existingDept->{
                    existingDept.setId(id);
                    existingDept.setName(department.getName());
                    existingDept.setDescription(department.getDescription());
                    return repository.save(existingDept);
                });
    }
}
