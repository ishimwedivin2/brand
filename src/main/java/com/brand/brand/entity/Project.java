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
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private  String description;
    private String gitHubLink;
    private String imageUrl; // Optional for thumbnails

    @Column(length = 5000) //Allow longer descriptions
    private String details;

    private LocalDate dateAdded;



}
