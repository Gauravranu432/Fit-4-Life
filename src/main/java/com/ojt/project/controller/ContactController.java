package com.ojt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ojt.project.entity.ContactForm;
import com.ojt.project.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow all origins, adjust as needed
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Submit Contact Form
    @PostMapping("/submitContactForm")
    public ResponseEntity<String> submitContactForm(@RequestBody ContactForm contactForm) {
        contactService.saveContactForm(contactForm);
        return new ResponseEntity<>("Form submitted successfully!", HttpStatus.OK);
    }

    // Fetch All Contact Forms
    @GetMapping("/getAllContactForms")
    public ResponseEntity<List<ContactForm>> getAllContactForms() {
        List<ContactForm> contactForms = contactService.getAllContactForms();
        return new ResponseEntity<>(contactForms, HttpStatus.OK);
    }

    // Delete a Contact Form by ID
    @DeleteMapping("/deleteContactForm/{id}")
    public ResponseEntity<String> deleteContactForm(@PathVariable Long id) {
        contactService.deleteContactFormById(id);
        return new ResponseEntity<>("Contact Form deleted successfully!", HttpStatus.OK);
    }

    // Handle exceptions
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
 // Count All Messages
    @GetMapping("/messages/count")
    public ResponseEntity<Long> countMessages() {
        long messageCount = contactService.countMessages();
        return new ResponseEntity<>(messageCount, HttpStatus.OK);
    }
}
