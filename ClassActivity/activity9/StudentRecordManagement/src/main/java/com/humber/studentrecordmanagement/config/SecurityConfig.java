package com.humber.studentrecordmanagement.config;

import com.humber.studentrecordmanagement.security.CustomUserDetailsService;
import com.humber.studentrecordmanagement.security.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password encoder bean.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Custom UserDetailsService bean.
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // Authentication provider using our custom UserDetailsService.
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    // Declare our custom token authentication filter.
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    // Security filter chain using our custom token filter.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider(passwordEncoder(), userDetailsService()))
                .authorizeHttpRequests(authorize -> authorize
                        // Allow public access to authentication endpoints.
                        .requestMatchers("/api/auth/**").permitAll()
                        // Admin endpoints require ADMIN role.
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // Student endpoints require STUDENT or ADMIN role.
                        .requestMatchers("/api/student/**").hasAnyRole("STUDENT", "ADMIN")
                        .anyRequest().authenticated()
                )
                // Add our token authentication filter before the standard username/password filter.
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults()); // Retained for fallback if needed.
        return http.build();
    }
}
