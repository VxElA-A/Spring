package com.example.DZfive.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

//    @OneToMany(mappedBy = "employee")
//    private List<EmployeeProject> employeeProjectList;
}


