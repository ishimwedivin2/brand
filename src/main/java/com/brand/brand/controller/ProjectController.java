package com.brand.brand.controller;

import com.brand.brand.entity.Project;
import com.brand.brand.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Project updatedProj = projectService.updateProject(id, updatedProject);
        if (updatedProj != null) {
            return new ResponseEntity<>(updatedProj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get all projects ordered by date added (descending)
    @GetMapping("/ordered/desc")
    public ResponseEntity<List<Project>> getProjectsOrderedByDateDesc() {
        List<Project> projects = projectService.getProjectsOrderedByDateDesc();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get all projects ordered by date added (ascending)
    @GetMapping("/ordered/asc")
    public ResponseEntity<List<Project>> getProjectsOrderedByDateAsc() {
        List<Project> projects = projectService.getProjectsOrderedByDateAsc();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get projects by title
    @GetMapping("/title")
    public ResponseEntity<List<Project>> getProjectsByTitle(@RequestParam String title) {
        List<Project> projects = projectService.getProjectsByTitle(title);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get projects by description
    @GetMapping("/description")
    public ResponseEntity<List<Project>> getProjectsByDescription(@RequestParam String description) {
        List<Project> projects = projectService.getProjectsByDescription(description);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get projects by GitHub link
    @GetMapping("/github-link")
    public ResponseEntity<List<Project>> getProjectsByGitHubLink(@RequestParam String gitHubLink) {
        List<Project> projects = projectService.getProjectsByGitHubLink(gitHubLink);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Delete a project by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
