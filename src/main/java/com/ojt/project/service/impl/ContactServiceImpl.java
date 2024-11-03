package com.ojt.project.service.impl;

import com.ojt.project.entity.ContactForm;
import com.ojt.project.repository.ContactFormRepository;
import com.ojt.project.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactFormRepository contactFormRepository;

    @Autowired
    public ContactServiceImpl(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    @Override
    public void saveContactForm(ContactForm contactForm) {
        contactFormRepository.save(contactForm);
    }

    @Override
    public List<ContactForm> getAllContactForms() {
        return contactFormRepository.findAll(); // Fetch all contact form entries
    }

    @Override
    public void deleteContactFormById(Long id) {
        contactFormRepository.deleteById(id);   // Delete contact form by ID
    }
    
    @Override
    public long countMessages() {
        return contactFormRepository.countAllMessages(); // Count all messages
    }
}