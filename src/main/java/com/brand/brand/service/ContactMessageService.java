package com.brand.brand.service;

import com.brand.brand.entity.ContactMessage;
import com.brand.brand.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    // Create a new contact message
    public ContactMessage createMessage(ContactMessage contactMessage) {
        contactMessage.setSentAt(LocalDate.now()); // Set the sent date as today's date
        return contactMessageRepository.save(contactMessage);
    }

    // Update an existing contact message
    public ContactMessage updateMessage(Long id, ContactMessage updatedMessage) {
        Optional<ContactMessage> existingMessageOptional = contactMessageRepository.findById(id);
        if (existingMessageOptional.isPresent()) {
            ContactMessage existingMessage = existingMessageOptional.get();
            existingMessage.setNames(updatedMessage.getNames());
            existingMessage.setEmail(updatedMessage.getEmail());
            existingMessage.setPhone(updatedMessage.getPhone());
            existingMessage.setMessage(updatedMessage.getMessage());
            existingMessage.setSentAt(updatedMessage.getSentAt() != null ? updatedMessage.getSentAt() : existingMessage.getSentAt());
            return contactMessageRepository.save(existingMessage);
        }
        return null; // Or throw an exception if message not found
    }

    // Get a contact message by ID
    public Optional<ContactMessage> getMessageById(Long id) {
        return contactMessageRepository.findById(id);
    }

    // Get all contact messages
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }

    // Get all contact messages ordered by sent date (descending)
    public List<ContactMessage> getMessagesOrderedByDateDesc() {
        return contactMessageRepository.findAllByOrderBySentAtDesc();
    }

    // Get all contact messages ordered by sent date (ascending)
    public List<ContactMessage> getMessagesOrderedByDateAsc() {
        return contactMessageRepository.findAllByOrderBySentAtAsc();
    }

    // Get messages by sender name (search functionality)
    public List<ContactMessage> getMessagesByName(String name) {
        return contactMessageRepository.findByNamesContaining(name);
    }

    // Get messages by sender email (search functionality)
    public List<ContactMessage> getMessagesByEmail(String email) {
        return contactMessageRepository.findByEmailContaining(email);
    }

    // Get messages by sender phone (search functionality)
    public List<ContactMessage> getMessagesByPhone(String phone) {
        return contactMessageRepository.findByPhoneContaining(phone);
    }

    // Get messages by message content (search functionality)
    public List<ContactMessage> getMessagesByContent(String content) {
        return contactMessageRepository.findByMessageContaining(content);
    }

    // Delete a contact message by ID
    public void deleteMessage(Long id) {
        contactMessageRepository.deleteById(id);
    }

    // Check if a contact message with the given email exists
    public boolean emailExists(String email) {
        return contactMessageRepository.findByEmail(email).isPresent();
    }
}
