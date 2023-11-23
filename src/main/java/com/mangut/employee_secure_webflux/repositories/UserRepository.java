package com.mangut.employee_secure_webflux.repositories;

import com.mangut.employee_secure_webflux.models.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Long> {
    Mono<User> findByEmail(String email);
}
