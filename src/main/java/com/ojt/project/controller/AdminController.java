package com.ojt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ojt.project.service.AdminUserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/login")
    public boolean login(@RequestParam String email, @RequestParam String password) {
        return adminUserService.validateUser(email, password);
    }
}