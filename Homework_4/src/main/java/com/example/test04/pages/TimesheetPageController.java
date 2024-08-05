package com.example.test04.pages;

import com.example.test04.model.Project;
import com.example.test04.service.TimesheetPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Timesheet page controller.
 */
@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService service;

    /**
     * Gets all timesheets.
     *
     * @param model the model
     * @return the all timesheets
     */
    @GetMapping
    public String getAllTimesheets(Model model) {
        List<TimeSheetPageDto> timesheets = service.findAll();
        model.addAttribute("timesheets", timesheets);
        return "timesheets-page.html";
    }

    /**
     * Gets timesheet page.
     *
     * @param id    the id
     * @param model the model
     * @return the timesheet page
     */
    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model) {
        Optional<TimeSheetPageDto> timesheetOpt = service.findById(id);
        if (timesheetOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("timesheet", timesheetOpt.get());
        return "timesheet-page.html";
    }

    /**
     * Gets project page.
     *
     * @param projectId the project id
     * @param model     the model
     * @return the project page
     */
    @GetMapping("/project/{projectId}")
    public String getProjectPage(@PathVariable Long projectId, Model model) {
        Optional<Project> projectOpt = service.findProjectById(projectId);
        if (projectOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("project", projectOpt.get());
        return "project-page.html";
    }
}



