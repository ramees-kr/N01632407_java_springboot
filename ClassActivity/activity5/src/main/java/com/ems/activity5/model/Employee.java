package com.ems.activity5.model;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private int emp_id;

    @NotBlank(message = "First name should not be empty")
    @Size(min = 5, max = 20, message = "First name must be between 5 and 20 characters")
    private String emp_firstname;

    @NotBlank(message = "Last name should not be empty")
    private String emp_lastname;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String emp_email;
}
