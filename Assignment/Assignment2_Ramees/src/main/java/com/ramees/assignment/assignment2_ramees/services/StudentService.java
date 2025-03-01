package com.ramees.assignment.assignment2_ramees.services;

import com.ramees.assignment.assignment2_ramees.Repositories.StudentRepository;
import com.ramees.assignment.assignment2_ramees.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        logger.info("Fetching all students");
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student findByUsername(String username) {
        logger.info("Fetching student by username from the database");
        return studentRepository.findByUsername((username));
    }

    public Student findByUsernameAndPassword(String username, String password) {
        logger.info("Fetching student by username and password from the database");
        Student student = studentRepository.findByUsernameAndPassword(username, password);
        if(student != null && student.getStatus().equals("ACTIVE")) {
            return student;
        }
        logger.info("Student not found or inactive");
        return null;
    }

    public void addStudent(Student student) {
        logger.info("Adding a new student: {}", student.getUsername());
        studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        logger.info("Deleting student: {}", student.getUsername());
        student.setStatus("INACTIVE");
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        logger.info("Updating student: {}", student.getUsername());
        student.setModified_date(new Date(System.currentTimeMillis()));
        studentRepository.save(student);
    }
}
