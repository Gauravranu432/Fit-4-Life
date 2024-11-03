package com.ojt.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojt.project.entity.AdminUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
}