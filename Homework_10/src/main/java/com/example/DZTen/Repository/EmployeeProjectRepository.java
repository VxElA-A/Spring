package com.example.DZTen.Repository;

import com.example.DZTen.Model.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
}

