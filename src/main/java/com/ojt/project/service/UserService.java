package com.ojt.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ojt.project.repository.UserRepository;
import com.ojt.project.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    // Method to authenticate user by email and password
    public User authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);

        // Check if user exists with the given email and password
        return userOptional.orElse(null); // Return user if found, or null if authentication fails
    }

    // Method to update a user's details
    public User updateUser(User user) {
        return userRepository.save(user); // Save and return the updated user
    }

    // Method to delete a user by ID
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false; // Could throw an exception instead of returning false
        }
    }

    // Method to get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Save a new user to the database
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public boolean updatePassword(String email, String newPassword) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setPassword(newPassword); // Consider encrypting the password here
                userRepository.save(user);
                return true;
            } else {
                System.out.println("User not found with email: " + email);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error updating password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
