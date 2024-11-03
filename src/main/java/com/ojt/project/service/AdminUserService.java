package com.ojt.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojt.project.entity.AdminUser;
import com.ojt.project.repository.AdminUserRepository;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser findByEmail(String email) {
        return adminUserRepository.findById(email).orElse(null);
    }

    public boolean validateUser(String email, String password) {
        AdminUser user = findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}