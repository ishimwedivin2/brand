package com.brand.brand.repository;

import com.brand.brand.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    Optional<ContactMessage> findByEmail(String email); // Find a message by email
    List<ContactMessage> findByNamesContaining(String names); // Search for messages by name
    List<ContactMessage> findByEmailContaining(String email); // Search for messages by email
    List<ContactMessage> findByPhoneContaining(String phone); // Search for messages by phone number
    List<ContactMessage> findByMessageContaining(String message); // Search for messages by content
    List<ContactMessage> findAllByOrderBySentAtDesc(); // Get messages ordered by sent date (descending)
    List<ContactMessage> findAllByOrderBySentAtAsc(); // Get messages ordered by sent date (ascending)
}
