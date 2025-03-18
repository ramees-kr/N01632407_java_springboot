package com.humber.studentrecordmanagement.repository;


import com.humber.studentrecordmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailAndActiveTrue(String email);
}
