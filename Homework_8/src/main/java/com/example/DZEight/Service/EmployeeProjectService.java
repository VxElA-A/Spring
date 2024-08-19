package com.example.DZEight.Service;

import com.example.DZEight.Repository.EmployeeProjectRepository;
import com.example.DZEight.Repository.EmployeeRepository;
import com.example.DZEight.Repository.ProjectRepository;
import com.example.DZEight.Model.Employee;
import com.example.DZEight.Model.EmployeeProject;
import com.example.DZEight.Model.Project;
import org.springframework.stereotype.Service;


@Service
public class EmployeeProjectService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeProjectRepository employeeProjectRepository;

    public EmployeeProjectService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, EmployeeProjectRepository employeeProjectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.employeeProjectRepository = employeeProjectRepository;
    }

    public EmployeeProject addEmployeeToProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + employeeId + " not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + projectId + " not found"));

        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setEmployee(employee);
        employeeProject.setProject(project);

        return employeeProjectRepository.save(employeeProject);
    }
}


//@Service
//public class EmployeeProjectService {
//
//    private final EmployeeRepository employeeRepository;
//    private final ProjectRepository projectRepository;
//    private final EmployeeProjectRepository employeeProjectRepository;
//
//    public EmployeeProjectService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, EmployeeProjectRepository employeeProjectRepository) {
//        this.employeeRepository = employeeRepository;
//        this.projectRepository = projectRepository;
//        this.employeeProjectRepository = employeeProjectRepository;
//    }
//
//    @Transactional
//    public EmployeeProject addEmployeeToProject(Long employeeId, Long projectId) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
//
//        EmployeeProject employeeProject = new EmployeeProject();
//        employeeProject.setEmployee(employee);
//        employeeProject.setProject(project);
//
//        return employeeProjectRepository.save(employeeProject);
//    }
//}

