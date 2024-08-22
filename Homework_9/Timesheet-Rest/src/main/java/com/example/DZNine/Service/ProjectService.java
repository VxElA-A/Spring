package com.example.DZNine.Service;

import com.example.DZNine.Model.Employee;
import com.example.DZNine.Model.Project;
import com.example.DZNine.Model.Timesheet;
import com.example.DZNine.Repository.EmployeeRepository;
import com.example.DZNine.Repository.ProjectRepository;
import com.example.DZNine.Repository.TimesheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
    }

    private Project findProjectOrThrow(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project with id " + projectId + " does not exist"));
    }


    public Optional<Project> findById(Long id) {

        return Optional.of(findProjectOrThrow(id));
    }

    public Optional<Project> findByName(String name) {

        return Optional.ofNullable(projectRepository.findByProjectName(name)
                .orElseThrow(() -> new NoSuchElementException("Project with name " + name + " does not exist")));
    }


    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {

        if (projectRepository.findByProjectName(project.getProjectName()).isPresent()) {
            throw new IllegalArgumentException("Project with name " + project.getProjectName() + " already exists");
        }

        return projectRepository.save(project);
    }

    public void deleteById(Long id) {

        findProjectOrThrow(id);
        projectRepository.deleteById(id);
    }

    public List<Employee> findProjectEmployees(Long id) {

        findProjectOrThrow(id);
        return projectRepository.findProjectEmployees(id);
    }

    public List<Timesheet> findProjectTimesheets(Long projectId) {

        findProjectOrThrow(projectId);
        return timesheetRepository.findAllByTimesheetProjectId(projectId);
    }

}