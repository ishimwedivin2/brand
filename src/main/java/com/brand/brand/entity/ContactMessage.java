package com.brand.brand.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_msg")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String names;
    private String email;
    private String phone;

    @Column(length = 2000) //Longer messages
    private String message;

    private LocalDate sentAt;





}
