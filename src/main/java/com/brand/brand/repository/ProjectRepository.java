package com.brand.brand.repository;

import com.brand.brand.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByTitle(String title); // Check if a project with this title exists
    List<Project> findByTitleContaining(String title); // Search for projects by title
    List<Project> findByDescriptionContaining(String description); // Search for projects by description
    List<Project> findByGitHubLinkContaining(String gitHubLink); // Search for projects by GitHub link
    List<Project> findAllByOrderByDateAddedDesc(); // Get projects ordered by date (descending)
    List<Project> findAllByOrderByDateAddedAsc(); // Get projects ordered by date (ascending)
}
