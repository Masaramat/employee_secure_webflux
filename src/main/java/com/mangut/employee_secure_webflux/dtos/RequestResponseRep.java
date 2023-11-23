package com.mangut.employee_secure_webflux.dtos;

public interface RequestResponseRep<T> {
    T getData();
    String getMessage();
}
