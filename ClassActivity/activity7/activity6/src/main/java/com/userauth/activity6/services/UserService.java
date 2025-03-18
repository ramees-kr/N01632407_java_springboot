package com.userauth.activity6.services;

import com.userauth.activity6.models.User;
import com.userauth.activity6.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        logger.info("Adding new user: {}", user.getUserName());
        userRepository.save(user);
    }
    public User userLogin(String userName, String password) {
        List<User> userList = userRepository.findByUserName(userName);
        return userList.stream()
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
