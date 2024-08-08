package com.example.DZfive.Controller;

import com.example.DZfive.Model.Employee;
import com.example.DZfive.Model.Project;
import com.example.DZfive.Model.Timesheet;
import com.example.DZfive.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getEmployeeProjects(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeProjects(id));
    }

//    Создать ресурс /employees/{id}/timesheets - получить все таймшиты по сотруднику
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getEmployeeTimesheets(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeTimesheets(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
        return employeeService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

