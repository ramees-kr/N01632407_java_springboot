package com.ems.activity3.model;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    int emp_id;
    String emp_firstname;
    String emp_lastname;
    String emp_email;
}
