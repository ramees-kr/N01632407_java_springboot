package com.example.studentrecordmanagement.service;

import com.example.studentrecordmanagement.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<String, Student> studentMap = new HashMap<>();

    @PostConstruct
    public void init() {
        // Pre-populate with three records
        Student s1 = new Student("S001", "Ramees KR", 20, "Male", "test@example.com", "Toronto", LocalDate.of(2003, 3, 15));
        Student s2 = new Student("S002", "John Honai", 22, "Male", "john@example.com", "Kochi", LocalDate.of(2001, 7, 20));
        Student s3 = new Student("S003", "Reshma K", 21, "Female", "resh@example.com", "New York", LocalDate.of(2002, 5, 10));

        studentMap.put(s1.getId(), s1);
        studentMap.put(s2.getId(), s2);
        studentMap.put(s3.getId(), s3);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentMap.values();
    }

    @Override
    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }
}
