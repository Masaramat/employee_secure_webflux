package com.mangut.employee_secure_webflux.dtos;

import com.mangut.employee_secure_webflux.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String password;

}
