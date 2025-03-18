package com.humber.studentrecordmanagement.service;
import com.humber.studentrecordmanagement.entity.Student;
import com.humber.studentrecordmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        Optional<Student> existing = studentRepository.findByEmailAndActiveTrue(student.getEmail());
        if(existing.isPresent()){
            throw new RuntimeException("Student already registered with this email");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAddress(updatedStudent.getAddress());
        student.setPassword(updatedStudent.getPassword());
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
        if (!student.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Credentials");
        }
        return student;
    }
}
