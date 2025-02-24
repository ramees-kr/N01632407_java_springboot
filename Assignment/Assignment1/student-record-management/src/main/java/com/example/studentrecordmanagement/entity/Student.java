package com.example.studentrecordmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String city;
    private LocalDate dateOfBirth;
}
