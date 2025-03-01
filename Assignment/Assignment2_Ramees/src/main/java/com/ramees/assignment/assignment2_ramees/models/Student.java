package com.ramees.assignment.assignment2_ramees.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    private String password;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;
    private String address;
    @NotNull(message = "Date of Birth is mandatory")
    private Date dob;
    private Date created_date = new Date(System.currentTimeMillis());
    private Date modified_date = new Date(System.currentTimeMillis());
    private String status="ACTIVE";

    public Student(String username, String password, String name, String email, String address, Date dob, Date created_date, Date modified_date, String status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.status = status;
    }
}
