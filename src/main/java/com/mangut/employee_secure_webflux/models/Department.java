package com.mangut.employee_secure_webflux.models;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "departments")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    private Long id;

    @NotBlank
    @Column(value = "name")
    private String name;

    @NotBlank
    @Column(value = "description")
    private String description;
}
