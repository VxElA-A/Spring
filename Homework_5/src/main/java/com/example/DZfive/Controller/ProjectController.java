package com.example.DZfive.Controller;


import com.example.DZfive.Model.Employee;
import com.example.DZfive.Model.Project;
import com.example.DZfive.Model.Timesheet;
import com.example.DZfive.Service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getProjectEmployees(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findProjectEmployees(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Project> getProjectByName(@PathVariable String name) {
        return projectService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.findProjectTimesheets(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

