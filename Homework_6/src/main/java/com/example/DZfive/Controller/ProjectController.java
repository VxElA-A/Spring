package com.example.DZfive.Controller;


import com.example.DZfive.Model.Employee;
import com.example.DZfive.Model.Project;
import com.example.DZfive.Model.Timesheet;
import com.example.DZfive.Service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
@Tag(name = "Project Controller", description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Получение всех проектов",
            description = "Получение всех проектов из базы данных")
    @API.Found
    @API.ServerError
    @GetMapping
//    @Parameter(name = "project", description = "Проект") @RequestParam(required = false) String project
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @Operation(summary = "Получение проекта по ID",
            description = "Получение проекта по его ID из БД",
            responses = {
//            @ApiResponse(description = "Проект найден", responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class))),
//            @ApiResponse(description = "Проект не найден", responseCode = "404", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
//            @ApiResponse(description = "Ошибка сервера", responseCode = "500", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @API.Found
    @API.NotFound
    @API.ServerError
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@Parameter(description = "id проекта") @PathVariable Long id) {
        Project projectNotFound = projectService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        return ResponseEntity.ok(projectNotFound);
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение списка сотрудников проекта по ID",
            description = "Получение списка сотрудников проекта по ID из базы данных")
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getProjectEmployees(@Parameter(description = "id проекта",
            schema = @Schema(type = "integer", format = "int64")) @PathVariable Long id) {
        return ResponseEntity.ok(projectService.findProjectEmployees(id));
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение проекта по названию",
            description = "Получение по названию проекта")
    @GetMapping("/name/{name}")
    public ResponseEntity<Project> getProjectByName(@Parameter(description = "name проекта",
            schema = @Schema(type = "string")) @PathVariable String name) {
        return projectService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение списка табелей проекта по ID",
            description = "Получение списка табелей проекта по ID из базы данных")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@Parameter(description = "id проекта",
            schema = @Schema(type = "integer", format = "int64")) @PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.findProjectTimesheets(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Создание нового проекта",
            description = "Создание нового проекта в базе данных")
    @API.Found
    @API.ServerError
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Удаление проекта по ID",
            description = "Удаление проекта по ID из базы данных")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@Parameter(description = "id проекта",
            schema = @Schema(type = "integer", format = "int64")) @PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

