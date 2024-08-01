package com.example.test03.repository;

import com.example.test03.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {
    private static long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(t -> Objects.equals(t.getProjectId(), id))
                .findFirst();
    }

    public List<Project> getAll() {
        return List.copyOf(projects);
    }

    public Project create(Project project) {
        project.setProjectId(sequence++);
        projects.add(project);
        return project;
    }

    public void delete(Long id) {
        projects.removeIf(t -> Objects.equals(t.getProjectId(), id));
    }
}
