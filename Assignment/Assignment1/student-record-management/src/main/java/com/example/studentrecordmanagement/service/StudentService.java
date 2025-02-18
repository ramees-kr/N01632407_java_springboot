package com.example.studentrecordmanagement.service;
import com.example.studentrecordmanagement.entity.Student;

import java.util.Collection;

public interface StudentService {
    Collection<Student> getAllStudents();
    void addStudent(Student student);
    Student getStudentById(String id);
}
