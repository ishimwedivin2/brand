package com.brand.brand.service;

import com.brand.brand.entity.User;
import com.brand.brand.repository.UserRepository;
import com.brand.brand.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or update user (Save operation)
    public User saveUser(User user) {
        // No password encoding, just save as is
        return userRepository.save(user);
    }

    // Find user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Find user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user information
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            // Update password only if provided
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            return userRepository.save(existingUser);
        }
        return null; // Or throw an exception if user not found
    }

    // Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Check if username exists
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Assign a role to a user
    public User assignRole(Long userId, Role role) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(role);
            return userRepository.save(user);
        }
        return null;
    }

    // Change user's password
    public User changePassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword); // Set new password without encoding
            return userRepository.save(user);
        }
        return null;
    }

    // Get user by role
    public List<User> getUsersByRole(Role role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals(role))
                .toList();
    }
}
