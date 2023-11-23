package com.mangut.employee_secure_webflux.repositories;

import com.mangut.employee_secure_webflux.models.Department;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface DepartmentRepository extends R2dbcRepository<Department, Long> {
}
