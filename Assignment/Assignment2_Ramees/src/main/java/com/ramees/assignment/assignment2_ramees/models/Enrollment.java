package com.ramees.assignment.assignment2_ramees.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicationNo;
    @Column(nullable = false)
    private Long studentId;
    @Column(nullable = false)
    private String programCode;
    private String status = "ACTIVE";
    private Date created_date = new Date(System.currentTimeMillis());
    private Date modified_date = new Date(System.currentTimeMillis());

    public Enrollment(Long studentId, String programCode) {
        this.studentId = studentId;
        this.programCode = programCode;
        this.modified_date = new Date(System.currentTimeMillis());
    }
}
