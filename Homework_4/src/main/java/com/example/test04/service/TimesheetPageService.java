package com.example.test04.service;

import com.example.test04.model.Project;
import com.example.test04.model.Timesheet;
import com.example.test04.pages.TimeSheetPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * The type Timesheet page service.
 */
@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimeSheetService timesheetService;
    private final ProjectService projectService;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<TimeSheetPageDto> findAll() {
        return timesheetService.getAll()
                .stream()
                .map(this::convert)
                .toList();
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<TimeSheetPageDto> findById(Long id) {
        return timesheetService
                .getById(id)
                .map(this::convert);
    }

    /**
     * Find all by project id list.
     *
     * @param projectId the project id
     * @return the list
     */
    public List<TimeSheetPageDto> findAllByProjectId(Long projectId) {
        return timesheetService.getAllByProjectId(projectId)
                .stream()
                .map(this::convert)
                .toList();
    }

    /**
     * Find project by id optional.
     *
     * @param projectId the project id
     * @return the optional
     */
    public Optional<Project> findProjectById(Long projectId) {
        return projectService.getById(projectId);
    }

    private TimeSheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.getById(Long.valueOf(timesheet.getProjectId()))
                .orElseThrow();

        TimeSheetPageDto timesheetPageParameters = new TimeSheetPageDto();
        timesheetPageParameters.setProjectName(project.getName());
        timesheetPageParameters.setId(String.valueOf(timesheet.getId()));
        timesheetPageParameters.setProjectId(String.valueOf(timesheet.getProjectId()));
        timesheetPageParameters.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageParameters.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

        return timesheetPageParameters;
    }
}


