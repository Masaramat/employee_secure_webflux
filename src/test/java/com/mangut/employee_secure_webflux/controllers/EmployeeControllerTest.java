package com.mangut.employee_secure_webflux.controllers;

import com.mangut.employee_secure_webflux.models.Employee;
import com.mangut.employee_secure_webflux.services.EmployeeService;
import com.mangut.employee_secure_webflux.services.JWTService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(EmployeeController.class)
public class EmployeeControllerTest {
    @MockBean
    private EmployeeService service;

    @MockBean
    private JWTService jwtService;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void saveEmployeeTest() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjk5MjgzMTcxLCJ" +
                "leHAiOjE2OTkyODQwNzF9.1W4lKWFJ1puhakfg0XT9dZKD22ZSIt8uSCLVr_l1jpQONAark92fZiki_GH3GgWx";

        when(jwtService.generateToken("user")).thenReturn(token);
        Employee employee = Employee.builder()
                .email("innocent@gmail.com")
                .firstName("Innocent")
                .lastName("Mangut")
                .departmentId(1L)
                .phone("090987654323")
                .build();
        when(service.save(employee)).thenReturn(Mono.just(employee));

        webTestClient.post()
                .uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(employee))
                .header("Authorization", "Bearer " + token) // Provide the generated token here
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Employee.class)
                .getResponseBody()
                .blockFirst();


    }
}
