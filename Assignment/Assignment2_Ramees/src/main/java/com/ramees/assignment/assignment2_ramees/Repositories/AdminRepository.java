package com.ramees.assignment.assignment2_ramees.Repositories;

import com.ramees.assignment.assignment2_ramees.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByUsernameAndPassword(String username, String password);
}
