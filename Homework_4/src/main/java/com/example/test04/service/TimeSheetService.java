package com.example.test04.service;

import com.example.test04.model.Timesheet;
import com.example.test04.repository.TimeSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Time sheet service.
 */
@Service
public class TimeSheetService {

    private final TimeSheetRepository repository;
    private final ProjectService projectService;

    /**
     * Instantiates a new Time sheet service.
     *
     * @param repository     the repository
     * @param projectService the project service
     */
    @Autowired
    public TimeSheetService(TimeSheetRepository repository, ProjectService projectService) {
        this.repository = repository;
        this.projectService = projectService;
    }

    /**
     * If exists boolean.
     *
     * @param projectId the project id
     * @return the boolean
     */
    public boolean ifExists(Long projectId) {
        return repository.exists(projectId);
    }

    /**
     * Gets by id.
     *
     * @param id        the id
     * @param projectId the project id
     * @return the by id
     */
    public Optional<Timesheet> getById(Long id, Long projectId) {
        if (!ifExists(projectId)) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist.");
        }
        return repository.getById(id);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    /**
     * Gets all by project id.
     *
     * @param projectId the project id
     * @return the all by project id
     */
    public List<Timesheet> getAllByProjectId(Long projectId) {
        if (!ifExists(projectId)) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist.");
        }
        return repository.getAllByProjectId(projectId);
    }

    /**
     * Gets all by project id and date.
     *
     * @param projectId       the project id
     * @param createdAtAfter  the created at after
     * @param createdAtBefore the created at before
     * @return the all by project id and date
     */
    public List<Timesheet> getAllByProjectIdAndDate(Long projectId, LocalDate createdAtAfter, LocalDate createdAtBefore) {
        List<Timesheet> timesheets = repository.getAllByProjectId(projectId);
        return timesheets.stream()
                .filter(t -> (createdAtAfter == null || t.getCreatedAt().isAfter(createdAtAfter))
                        && (createdAtBefore == null || t.getCreatedAt().isBefore(createdAtBefore)))
                .collect(Collectors.toList());
    }

    /**
     * Create timesheet.
     *
     * @param timesheet the timesheet
     * @param projectId the project id
     * @return the timesheet
     */
    public Timesheet create(Timesheet timesheet, Long projectId) {
        if (!projectService.getById(projectId).isPresent()) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist.");
        }
        return repository.create(timesheet, projectId);
    }

    /**
     * Delete.
     *
     * @param id        the id
     * @param projectId the project id
     */
    public void delete(Long id, Long projectId) {
        if (!ifExists(projectId)) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist.");
        }
        repository.delete(id);
    }
}


