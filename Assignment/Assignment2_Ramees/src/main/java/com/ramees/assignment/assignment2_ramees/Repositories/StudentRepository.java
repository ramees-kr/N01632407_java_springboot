package com.ramees.assignment.assignment2_ramees.Repositories;

import com.ramees.assignment.assignment2_ramees.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
    Student findByUsernameAndPassword(String username, String password);
}
