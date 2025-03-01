package com.ramees.assignment.assignment2_ramees.Repositories;

import com.ramees.assignment.assignment2_ramees.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String> {
    public Program findByProgramCode(String programCode);

    List<Program> findAllByStatus(String status);
}
