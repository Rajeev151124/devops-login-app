package com.example.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.login.entity.User;
import com.example.login.repository.UserRepository;

@Controller
public class LoginController {

    private final UserRepository repo;

    public LoginController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String saveUser(@RequestParam String username,
                           @RequestParam String password) {

        repo.save(new User(null, username, password));
        return "success";
    }
}

