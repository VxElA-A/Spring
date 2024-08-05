package com.example.test04.repository;

import com.example.test04.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Project repository.
 */
@Repository
public class ProjectRepository {
    private static long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    /**
     * Exists boolean.
     *
     * @param projectId the project id
     * @return the boolean
     */
    public boolean exists(Long projectId) {
        return projects.stream()
                .anyMatch(t -> Objects.equals(t.getProjectId(), projectId));
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(t -> Objects.equals(t.getProjectId(), id))
                .findFirst();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Project> getAll() {
        return List.copyOf(projects);
    }

    /**
     * Create project.
     *
     * @param project the project
     * @return the project
     */
    public Project create(Project project) {
        project.setProjectId(sequence++);
        projects.add(project);
        return project;
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id) {
        projects.removeIf(t -> Objects.equals(t.getProjectId(), id));
    }
}
