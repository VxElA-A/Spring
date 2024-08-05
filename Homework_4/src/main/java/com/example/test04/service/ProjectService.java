package com.example.test04.service;

import com.example.test04.model.Project;
import com.example.test04.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Project service.
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * Instantiates a new Project service.
     *
     * @param repository the repository
     */
    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.projectRepository = repository;
    }

    /**
     * If exists boolean.
     *
     * @param projectId the project id
     * @return the boolean
     */
    public boolean ifExists(Long projectId) {
        return projectRepository.exists(projectId);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Project> getById(Long id) {
        if (!ifExists(id)) {
            throw new IllegalArgumentException("Project with ID " + id + " does not exist.");
        }
        return projectRepository.getById(id);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    /**
     * Create project.
     *
     * @param project the project
     * @return the project
     */
    public Project create(Project project) {
        return projectRepository.create(project);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id) {
        if (!ifExists(id)) {
            throw new IllegalArgumentException("Project with ID " + id + " does not exist.");
        }
        projectRepository.delete(id);
    }
}
