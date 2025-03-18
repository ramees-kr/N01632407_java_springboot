package com.humber.studentrecordmanagement.service;

import com.humber.studentrecordmanagement.entity.Student;
import com.humber.studentrecordmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public Student registerStudent(Student student) {
        Optional<Student> existing = studentRepository.findByEmailAndActiveTrue(student.getEmail());
        if(existing.isPresent()){
            throw new RuntimeException("Student already registered with this email");
        }
        // Encode the student's password before saving.
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAddress(updatedStudent.getAddress());
        // Update and encode password.
        student.setPassword(passwordEncoder.encode(updatedStudent.getPassword()));
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student studentLogin(String email, String password) {
        Student student = studentRepository.findByEmailAndActiveTrue(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (!passwordEncoder.matches(password, student.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        return student;
    }
}
