package com.example.DZEight.Service;

import com.example.DZEight.Aspect.Timer;
import com.example.DZEight.Repository.EmployeeRepository;
import com.example.DZEight.Repository.ProjectRepository;
import com.example.DZEight.Repository.TimesheetRepository;
import com.example.DZEight.Model.Employee;
import com.example.DZEight.Model.Project;
import com.example.DZEight.Model.Timesheet;
import org.slf4j.event.Level;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Timer(level = Level.WARN)
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