package com.ojt.project.service;

import com.ojt.project.entity.ContactForm;

import java.util.List;

public interface ContactService {
    void saveContactForm(ContactForm contactForm);
    List<ContactForm> getAllContactForms(); // New method to fetch all contact forms
    void deleteContactFormById(Long id);    // New method to delete a contact form by ID
    long countMessages(); // Method to count messages
}
