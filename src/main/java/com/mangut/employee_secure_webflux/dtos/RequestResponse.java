package com.mangut.employee_secure_webflux.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestResponse<T> implements RequestResponseRep<T> {
    private T data;
    private String message;
    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
