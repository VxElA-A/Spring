package com.example.DZEight.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "employee_project")
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @Column(name = "employee_project_id")
    private Long employeeProjectId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;



//    public void setEmployeeAndProject(Employee employee, Project project) {
//        this.employee = employee;
//        this.project = project;
//    }
}

