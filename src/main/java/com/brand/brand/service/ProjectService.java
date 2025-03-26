package com.brand.brand.service;

import com.brand.brand.entity.Project;
import com.brand.brand.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Create a new project
    public Project createProject(Project project) {
        project.setDateAdded(LocalDate.now()); // Set the date added as today's date
        return projectRepository.save(project);
    }

    // Update an existing project
    public Project updateProject(Long id, Project updatedProject) {
        Optional<Project> existingProjectOptional = projectRepository.findById(id);
        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();
            existingProject.setTitle(updatedProject.getTitle());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setGitHubLink(updatedProject.getGitHubLink());
            existingProject.setImageUrl(updatedProject.getImageUrl());
            existingProject.setDetails(updatedProject.getDetails());
            existingProject.setDateAdded(updatedProject.getDateAdded() != null ? updatedProject.getDateAdded() : existingProject.getDateAdded());
            return projectRepository.save(existingProject);
        }
        return null; // Or throw an exception if project not found
    }

    // Get a project by ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get all projects ordered by date added (descending)
    public List<Project> getProjectsOrderedByDateDesc() {
        return projectRepository.findAllByOrderByDateAddedDesc();
    }

    // Get all projects ordered by date added (ascending)
    public List<Project> getProjectsOrderedByDateAsc() {
        return projectRepository.findAllByOrderByDateAddedAsc();
    }

    // Get projects by title (search functionality)
    public List<Project> getProjectsByTitle(String title) {
        return projectRepository.findByTitleContaining(title);
    }

    // Get projects by description (search functionality)
    public List<Project> getProjectsByDescription(String description) {
        return projectRepository.findByDescriptionContaining(description);
    }

    // Get projects by GitHub link (search functionality)
    public List<Project> getProjectsByGitHubLink(String gitHubLink) {
        return projectRepository.findByGitHubLinkContaining(gitHubLink);
    }

    // Delete a project by ID
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // Check if a project with a given title already exists
    public boolean titleExists(String title) {
        return projectRepository.findByTitle(title).isPresent();
    }
}
