package com.ojt.project.repository;

import com.ojt.project.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {
	@Query("SELECT COUNT(c) FROM ContactForm c")
    long countAllMessages(); // Method to count all messages
}