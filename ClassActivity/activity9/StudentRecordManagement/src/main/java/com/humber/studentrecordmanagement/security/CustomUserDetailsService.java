package com.humber.studentrecordmanagement.security;

import com.humber.studentrecordmanagement.entity.Student;
import com.humber.studentrecordmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // For admin: use hard-coded credentials.
        if ("admin".equalsIgnoreCase(username)) {
            // Password "admin123" encoded with BCrypt.
            return User.builder()
                    .username("admin")
                    .password("$2a$10$PnSxAWezzl4hd2mXIsD2h.KWJSfjkFlh2jQuU.VjnXn5UyXvWpsf2")
                    .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))
                    .build();
        }
        // For students: load from database using email as username.
        Student student = studentRepository.findByEmailAndActiveTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT")))
                .build();
    }
}
