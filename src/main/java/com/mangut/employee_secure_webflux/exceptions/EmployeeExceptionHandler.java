package com.mangut.employee_secure_webflux.exceptions;

import com.mangut.employee_secure_webflux.dtos.RequestResponse;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RequestResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.status(404).body(new RequestResponse<>("", ex.getMessage()));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<RequestResponse<String>> handleMalformedJwtException(MalformedJwtException ex){
        return ResponseEntity.status(404).body(new RequestResponse<>("", "There is an error in your token"));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<RequestResponse<String>> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
        return ResponseEntity.status(404).body(new RequestResponse<>("", ex.getMessage()));
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<RequestResponse<String>> handleDepartmentNotFoundException(DepartmentNotFoundException ex){
        return ResponseEntity.status(404).body(new RequestResponse<>("", ex.getMessage()));
    }
}
