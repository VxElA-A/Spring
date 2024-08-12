package com.example.DZfive.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.List;

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


