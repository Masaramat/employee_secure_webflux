package com.mangut.employee_secure_webflux.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;




@Data
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(value = "email")
    private String email;

    @NotBlank(message = "first name cannot be blank")
    @Column(value = "first_name")
    private String firstName;

    @NotBlank(message = "Lastname cannot be empty")
    @Column(value = "last_name")
    private String lastName;

    @NotBlank(message = "Phone cannot be blank")
    @Column(value = "phone")
    private String phone;

    @Column(value = "department_id")
    private Long departmentId;

}
