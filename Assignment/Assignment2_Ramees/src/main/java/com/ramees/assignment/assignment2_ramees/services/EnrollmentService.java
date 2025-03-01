package com.ramees.assignment.assignment2_ramees.services;

import com.ramees.assignment.assignment2_ramees.Repositories.EnrollmentRepository;
import com.ramees.assignment.assignment2_ramees.Repositories.ProgramRepository;
import com.ramees.assignment.assignment2_ramees.models.Enrollment;
import com.ramees.assignment.assignment2_ramees.models.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

    private final EnrollmentRepository enrollmentRepository;
    private final ProgramRepository programRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, ProgramRepository programRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.programRepository = programRepository;
    }

    public void addEnrollment(Enrollment enrollment) {
        logger.info("Adding a new enrollment: {}, {}", enrollment.getStudentId(), enrollment.getProgramCode());
        enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Enrollment enrollment) {
        logger.info("Deleting enrollment: {}, {}", enrollment.getStudentId(), enrollment.getProgramCode());
        enrollment.setStatus("INACTIVE");
        enrollmentRepository.save(enrollment);
    }

    public void updateEnrollment(Enrollment enrollment) {
        logger.info("Updating enrollment: {}, {}", enrollment.getStudentId(), enrollment.getProgramCode());
        enrollmentRepository.save(enrollment);
    }

    public Enrollment findByStudentAndProgram(Long studentId, String programCode) {
        logger.info("Fetching enrollment for student: {}, program: {}", studentId, programCode);
        return enrollmentRepository.findByStudentIdAndProgramCode(studentId, programCode);
    }

    public List<Enrollment> findByStudent(Long studentId) {
        logger.info("Fetching enrollments for student: {}", studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Program> findProgramsByStudent(Long studentId) {
        logger.info("Fetching programs for student: {}", studentId);
        List<String> programIds = findByStudent(studentId).stream().map(Enrollment::getProgramCode).toList();
        List<Program> programs = programRepository.findAll();
        return programs.stream().filter(program -> programIds.contains(program.getProgramCode())).toList();
    }
}
