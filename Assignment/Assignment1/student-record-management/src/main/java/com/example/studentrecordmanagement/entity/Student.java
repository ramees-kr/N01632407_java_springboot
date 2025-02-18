package com.example.studentrecordmanagement.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PRIVATE)
public class Student {

    private String id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String city;
    private LocalDate dateOfBirth;
}
