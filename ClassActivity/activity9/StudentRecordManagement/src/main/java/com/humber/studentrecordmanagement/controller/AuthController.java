package com.humber.studentrecordmanagement.controller;

import com.humber.studentrecordmanagement.entity.Student;
import com.humber.studentrecordmanagement.security.TokenService;
import com.humber.studentrecordmanagement.security.CustomUserDetailsService;
import com.humber.studentrecordmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final StudentService studentService;
    private final TokenService tokenService;
    private final CustomUserDetailsService customUserDetailsService;

    // Student registration endpoint.
    @PostMapping("/student/register")
    public Student registerStudent(@RequestBody Student student) {
        Student savedStudent = studentService.registerStudent(student);
        log.info("Student registered: {}", savedStudent.getEmail());
        return savedStudent;
    }

    // Student login endpoint: returns a token if credentials are valid.
    @PostMapping("/student/login")
    public Map<String, String> studentLogin(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("username");
        String password = loginData.get("password");
        try {
            studentService.studentLogin(email, password);
            // Load user details to generate token.
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            String token = tokenService.generateToken(userDetails);
            log.info("Student logged in: {}", email);
            return Map.of("message", "Student login successful", "role", "student", "token", token);
        } catch (Exception e) {
            log.warn("Student login failed for: {}", email);
            return Map.of("message", "Invalid Credentials");
        }
    }

    // Admin login endpoint: returns a token if credentials are valid.
    @PostMapping("/admin/login")
    public Map<String, String> adminLogin(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        if ("admin".equalsIgnoreCase(username) && "admin123".equals(password)) {
            UserDetails adminUser = customUserDetailsService.loadUserByUsername("admin");
            String token = tokenService.generateToken(adminUser);
            log.info("Admin logged in successfully");
            return Map.of("message", "Admin login successful", "role", "admin", "token", token);
        } else {
            log.warn("Admin login failed for: {}", username);
            return Map.of("message", "Invalid Credentials");
        }
    }
}
