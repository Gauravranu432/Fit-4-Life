package com.ojt.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ojt.project.entity.Attendance;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByDate(String date);
}