package com.example.DZNine.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

//    @OneToMany(mappedBy = "project")
//    private List<EmployeeProject> employeeProjectList;
}


