package com.ramees.assignment.assignment2_ramees.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Id
    @Column(unique = true, nullable = false)
    private String programCode;
    @Column(nullable = false)
    private String programName;
    @Column(nullable = false)
    private String status = "ACTIVE";

}
