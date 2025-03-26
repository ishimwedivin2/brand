package com.brand.brand.controller;

import com.brand.brand.entity.ContactMessage;
import com.brand.brand.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;

    // Create a new contact message
    @PostMapping
    public ResponseEntity<ContactMessage> createMessage(@RequestBody ContactMessage contactMessage) {
        ContactMessage createdMessage = contactMessageService.createMessage(contactMessage);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    // Update an existing contact message
    @PutMapping("/{id}")
    public ResponseEntity<ContactMessage> updateMessage(@PathVariable Long id, @RequestBody ContactMessage updatedMessage) {
        ContactMessage updatedMsg = contactMessageService.updateMessage(id, updatedMessage);
        if (updatedMsg != null) {
            return new ResponseEntity<>(updatedMsg, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get a contact message by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContactMessage> getMessageById(@PathVariable Long id) {
        Optional<ContactMessage> message = contactMessageService.getMessageById(id);
        if (message.isPresent()) {
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all contact messages
    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        List<ContactMessage> messages = contactMessageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get all contact messages ordered by sent date (descending)
    @GetMapping("/ordered/desc")
    public ResponseEntity<List<ContactMessage>> getMessagesOrderedByDateDesc() {
        List<ContactMessage> messages = contactMessageService.getMessagesOrderedByDateDesc();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get all contact messages ordered by sent date (ascending)
    @GetMapping("/ordered/asc")
    public ResponseEntity<List<ContactMessage>> getMessagesOrderedByDateAsc() {
        List<ContactMessage> messages = contactMessageService.getMessagesOrderedByDateAsc();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get messages by sender name
    @GetMapping("/name")
    public ResponseEntity<List<ContactMessage>> getMessagesByName(@RequestParam String name) {
        List<ContactMessage> messages = contactMessageService.getMessagesByName(name);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get messages by sender email
    @GetMapping("/email")
    public ResponseEntity<List<ContactMessage>> getMessagesByEmail(@RequestParam String email) {
        List<ContactMessage> messages = contactMessageService.getMessagesByEmail(email);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get messages by sender phone
    @GetMapping("/phone")
    public ResponseEntity<List<ContactMessage>> getMessagesByPhone(@RequestParam String phone) {
        List<ContactMessage> messages = contactMessageService.getMessagesByPhone(phone);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get messages by content
    @GetMapping("/content")
    public ResponseEntity<List<ContactMessage>> getMessagesByContent(@RequestParam String content) {
        List<ContactMessage> messages = contactMessageService.getMessagesByContent(content);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Delete a contact message by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        contactMessageService.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
