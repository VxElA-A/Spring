package com.example.test04.repository;


import com.example.test04.model.Timesheet;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The type Time sheet repository.
 */
@Repository
public class TimeSheetRepository {

    private final ConcurrentHashMap<Long, Long> projectSequenceMap = new ConcurrentHashMap<>();
    private final List<Timesheet> timesheets = new ArrayList<>();

    private long getNextIdForProject(Long projectId) {
        return projectSequenceMap.merge(projectId, 1L, (oldValue, newValue) -> oldValue + 1);
    }

    /**
     * Exists boolean.
     *
     * @param projectId the project id
     * @return the boolean
     */
    public boolean exists(Long projectId) {
        return projectSequenceMap.containsKey(projectId);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Timesheet> getById(Long id) {
        return timesheets.stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    /**
     * Gets all by project id.
     *
     * @param projectId the project id
     * @return the all by project id
     */
    public List<Timesheet> getAllByProjectId(Long projectId) {
        return timesheets.stream()
                .filter(t -> Objects.equals(t.getProjectId(), projectId.toString()))
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
        timesheet.setId(getNextIdForProject(projectId));
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setProjectId(projectId.toString());
        timesheets.add(timesheet);
        return timesheet;
    }

    /**
     * Create timesheet.
     *
     * @param timesheet the timesheet
     * @return the timesheet
     */
    public Timesheet create(Timesheet timesheet) {
//        timesheet.setId(getNextIdForProject(projectId));
//        timesheet.setCreatedAt(LocalDate.now());
//        timesheet.setProjectId(projectId.toString());
        timesheets.add(timesheet);
        return timesheet;
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id) {
        timesheets.removeIf(t -> Objects.equals(t.getId(), id));
    }


}