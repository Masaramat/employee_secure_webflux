package com.mangut.employee_secure_webflux.exceptions;

import java.io.IOException;

public class DepartmentNotFoundException extends IOException {
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
