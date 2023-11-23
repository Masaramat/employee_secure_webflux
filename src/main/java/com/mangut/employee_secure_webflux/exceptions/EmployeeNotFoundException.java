package com.mangut.employee_secure_webflux.exceptions;

import java.io.IOException;

public class EmployeeNotFoundException extends IOException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
