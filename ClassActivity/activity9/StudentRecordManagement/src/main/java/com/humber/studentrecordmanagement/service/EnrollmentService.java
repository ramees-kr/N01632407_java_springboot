package com.humber.studentrecordmanagement.service;

import com.humber.studentrecordmanagement.entity.Enrollment;
import com.humber.studentrecordmanagement.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public Enrollment enrollStudentToCourse(Long studentId, Long courseId) {
        Optional<Enrollment> existing = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (existing.isPresent()) {
            throw new RuntimeException("Student already enrolled in this course");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        return enrollmentRepository.save(enrollment);
    }

    public String dropEnrollment(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
        return "Enrollment dropped successfully";
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}
