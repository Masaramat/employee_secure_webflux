package com.mangut.employee_secure_webflux.configs.security;

import com.mangut.employee_secure_webflux.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    private static UserRepository repository;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user = User.builder()
                .username("real")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, AuthConverter authConverter, AuthManager jwtAuthManager){
        AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(jwtAuthManager);
        jwtFilter.setServerAuthenticationConverter(authConverter);
        return http
                .authorizeExchange(auth->{
                    auth.pathMatchers("/auth/**").permitAll();
                    auth.anyExchange().authenticated();
                })
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();

    }
}
