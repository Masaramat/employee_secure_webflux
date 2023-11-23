package com.mangut.employee_secure_webflux.services;

import com.mangut.employee_secure_webflux.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataR2dbcTest
public class EmployeeServiceTest {
    @MockBean
    private EmployeeService service;

    @Test
    public void saveEmployeeTest(){
        Employee employee = Employee.builder()
                .email("innocent@gmail.com")
                .firstName("Innocent")
                .lastName("Mangut")
                .departmentId(1L)
                .phone("090987654323")
                .build();

        when(service.save(employee)).thenReturn(Mono.just(employee));

        Mono<Employee> savedEmployee = service.save(employee);

        assertThat(savedEmployee).isNotNull();

    }

    @Test
    public void findAllEmployeesTest() {
        // Insert sample data into the in-memory database
        Employee employee1 = Employee.builder()
                .email("employee1@gmail.com")
                .firstName("Employee")
                .lastName("One")
                .departmentId(1L)
                .phone("1234567890")
                .build();

        Employee employee2 = Employee.builder()
                .email("employee2@gmail.com")
                .firstName("Employee")
                .lastName("Two")
                .departmentId(1L)
                .phone("9876543210")
                .build();

        when(service.getAllEmployees()).thenReturn(Flux.just(employee1, employee2));

        Flux<Employee> allEmployees = service.getAllEmployees();

        StepVerifier.create(allEmployees)
                .assertNext(employee -> assertThat(employee.getLastName()).isEqualTo("One"))
                .assertNext(employee -> assertThat(employee.getLastName()).isEqualTo("Two"))
                .expectComplete()
                .verify();


    }
}
