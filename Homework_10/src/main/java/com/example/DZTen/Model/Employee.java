package com.example.DZTen.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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


//@Entity
//@Table(name = "employee")
//@Data
//public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "employee_id")
//    private Long employeeId;
//
//    @Column(name = "employee_name")
//    private String employeeName;
//}