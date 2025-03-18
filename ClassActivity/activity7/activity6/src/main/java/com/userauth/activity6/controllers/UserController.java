package com.userauth.activity6.controllers;

import com.userauth.activity6.models.User;
import com.userauth.activity6.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Objects;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display the login page (landing page)
    @GetMapping("/")
    public String showLoginPage() {
        return "index";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        Model model) {
        logger.info("Attempting login for user: {}", userName);
        User user = userService.userLogin(userName, password);
        if (Objects.nonNull(user)) {
            model.addAttribute("message", "Welcome, " + user.getUserName() + "!");
            return "home";
        } else {
            model.addAttribute("error", "Invalid Username/Password");
            return "index";
        }
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("userName") String userName,
                           @RequestParam("password") String password,
                           @RequestParam("gender") String gender,
                           @RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob,
                           Model model) {
        logger.info("Registering new user: {}", userName);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setGender(gender);
        user.setDob(dob);
        user.setCreatedDate(LocalDate.now());
        user.setEnabled(true);

        try {
            userService.addUser(user);
        } catch (Exception ex) {
            logger.error("Error occurred while registering user: {}", ex.getMessage());
            model.addAttribute("error", "User already exists with this username or email. Please choose another.");
            return "register";
        }

        model.addAttribute("message", "User registered successfully. Please login.");
        return "redirect:/";
    }
}
