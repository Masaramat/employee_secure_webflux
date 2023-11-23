package com.mangut.employee_secure_webflux.controllers;

import com.mangut.employee_secure_webflux.dtos.LoginRequest;
import com.mangut.employee_secure_webflux.dtos.RequestResponse;
import com.mangut.employee_secure_webflux.services.AuthenticationService;
import com.mangut.employee_secure_webflux.services.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final ReactiveUserDetailsService users;
    private final JWTService jwtService;

    private final PasswordEncoder encoder;

    public AuthController(
            AuthenticationService authenticationService,
            ReactiveUserDetailsService users,
            JWTService jwtService,
            PasswordEncoder encoder)
    {
        this.authenticationService = authenticationService;
        this.users = users;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @GetMapping(value = "/auth")
    public Mono<ResponseEntity<RequestResponse<String>>> auth(){
        return Mono.just(ResponseEntity.ok(
                new RequestResponse<>("Welcome to secured endpoint", "")
        ));
    }

    @PostMapping(value = "/login")
    public Mono<ResponseEntity<RequestResponse<String>>> login(@RequestBody LoginRequest user){
        Mono<UserDetails> foundUser = users.findByUsername(user.getEmail()).defaultIfEmpty(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        });


        return foundUser.map(u -> {
            if(u.getUsername() == null){
                return ResponseEntity.status(404).body(
                        new RequestResponse<>("", "User not found")
                );
            }
            if(encoder.matches(user.getPassword(), u.getPassword())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RequestResponse<>(jwtService.generateToken(u.getUsername()), "Success")
                );
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new RequestResponse<>("", "Invalid Credentials")
            );
        });
    }


}
