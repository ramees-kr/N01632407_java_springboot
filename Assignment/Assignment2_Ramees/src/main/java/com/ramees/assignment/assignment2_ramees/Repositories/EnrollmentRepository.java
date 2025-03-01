package com.ramees.assignment.assignment2_ramees.Repositories;

import com.ramees.assignment.assignment2_ramees.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Enrollment findByStudentIdAndProgramCode(Long studentId, String programCode);

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findAllByStatus(String status);
}
