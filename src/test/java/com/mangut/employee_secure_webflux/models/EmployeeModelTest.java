package com.mangut.employee_secure_webflux.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeModelTest {
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEmployeeValidation(){
        Employee employee = new Employee();
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertFalse(violations.isEmpty());
        int errorNo = 0;

        for(ConstraintViolation<Employee> violation : violations){
            errorNo++;
            String errorMessage = violation.getMessage();
            System.out.println("Validation Error "  +errorNo + ": " + errorMessage);
        }

        assertEquals(4, errorNo);
    }

    @Test
    public void testEmployeeSuccessValidation(){
        Employee employee = new Employee(1L, "employee@gmail.com", "Mangut", "Innocent", "09098787656", 1L);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertTrue(violations.isEmpty());
        int errorNo = 0;

        for(ConstraintViolation<Employee> violation : violations){
            errorNo++;
            String errorMessage = violation.getMessage();
            System.out.println("Validation Error "  +errorNo + ": " + errorMessage);
        }

        assertEquals(0, errorNo);
    }
    @Test
    public void testEmployeeInvalidEmailValidation(){
        Employee employee = new Employee(1L, "employeegmail.com", "Mangut", "Innocent", "09098787656", 1L);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertFalse(violations.isEmpty());
        int errorNo = 0;

        for(ConstraintViolation<Employee> violation : violations){
            errorNo++;
            String errorMessage = violation.getMessage();
            System.out.println("Validation Error "  +errorNo + ": " + errorMessage);
        }

        assertEquals(1, errorNo);
    }
}
