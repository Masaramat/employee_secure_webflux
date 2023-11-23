package com.mangut.employee_secure_webflux.services;

import com.mangut.employee_secure_webflux.dtos.RegisterRequest;
import com.mangut.employee_secure_webflux.models.Role;
import com.mangut.employee_secure_webflux.models.User;
import com.mangut.employee_secure_webflux.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public Mono<Object> register(RegisterRequest registerRequest) {
       return repository.findByEmail(registerRequest.getEmail()).flatMap(
               existingUser-> Mono.error(new IllegalArgumentException("User exists")).switchIfEmpty(
                       repository.save(User.builder()
                               .name(registerRequest.getName())
                               .phone(registerRequest.getPhone())
                               .email(registerRequest.getEmail())
                               .password(passwordEncoder.encode(registerRequest.getPassword()))
                               .role(Role.USER)
                               .build()

                       )

               )

       );

    }

}
