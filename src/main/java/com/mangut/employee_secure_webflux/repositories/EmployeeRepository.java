package com.mangut.employee_secure_webflux.repositories;

import com.mangut.employee_secure_webflux.models.Employee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface EmployeeRepository extends R2dbcRepository<Employee, Long> {
}
