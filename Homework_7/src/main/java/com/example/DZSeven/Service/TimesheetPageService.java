package com.example.DZSeven.Service;

import com.example.DZSeven.Model.Project;
import com.example.DZSeven.Model.Timesheet;
import com.example.DZSeven.Pages.TimeSheetPageDto;
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

    private final TimesheetService timesheetService;
    private final ProjectService projectService;


    public List<TimeSheetPageDto> findAll() {
        return timesheetService.findAll()
                .stream()
                .map(this::convert)
                .toList();
    }


    public Optional<TimeSheetPageDto> findById(Long id) {
        return timesheetService
                .findById(id)
                .map(this::convert);
    }


    public List<TimeSheetPageDto> findAllByProjectId(Long projectId) {
        return timesheetService.findAllByTimesheetProjectId(projectId)
                .stream()
                .map(this::convert)
                .toList();
    }


    public Optional<Project> findProjectById(Long projectId) {
        return projectService.findById(projectId);
    }

    private TimeSheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.findById(Long.valueOf(timesheet.getTimesheetProjectId()))
                .orElseThrow();

        TimeSheetPageDto timesheetPageParameters = new TimeSheetPageDto();
        timesheetPageParameters.setProjectName(project.getProjectName());
        timesheetPageParameters.setId(String.valueOf(timesheet.getTimesheetId()));
        timesheetPageParameters.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
        timesheetPageParameters.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageParameters.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

        return timesheetPageParameters;
    }
}


