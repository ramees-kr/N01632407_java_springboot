package com.humber.studentrecordmanagement.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {

    // In-memory token store (token -> UserDetails)
    private ConcurrentHashMap<String, UserDetails> tokenStore = new ConcurrentHashMap<>();

    // Generate a new token and store user details.
    public String generateToken(UserDetails userDetails) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, userDetails);
        return token;
    }

    // Retrieve user details associated with a token.
    public UserDetails getUserDetails(String token) {
        return tokenStore.get(token);
    }

    // Remove a token (e.g., on logout).
    public void removeToken(String token) {
        tokenStore.remove(token);
    }
}
