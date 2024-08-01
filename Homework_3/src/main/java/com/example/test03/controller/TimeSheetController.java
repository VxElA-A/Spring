package com.example.test03.contoller;

import com.example.test03.model.Timesheet;
import com.example.test03.service.TimeSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}/timesheets")
public class TimeSheetController {

    private final TimeSheetService timesheetService;

    public TimeSheetController(TimeSheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long projectId, @PathVariable("id") Long id) {
        Optional<Timesheet> ts = timesheetService.getById(id, projectId);
        if (ts.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(@PathVariable Long projectId) {
        try {
            return ResponseEntity.ok(timesheetService.getAllByProjectId(projectId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/filter")
    public ResponseEntity<List<Timesheet>> getAllByDate(
            @PathVariable Long projectId,
            @RequestParam(required = false) String createdAtAfter,
            @RequestParam(required = false) String createdAtBefore) {

        LocalDate afterDate = createdAtAfter != null ? LocalDate.parse(createdAtAfter) : null;
        LocalDate beforeDate = createdAtBefore != null ? LocalDate.parse(createdAtBefore) : null;

        return ResponseEntity.ok(timesheetService.getAllByProjectIdAndDate(projectId, afterDate, beforeDate));
    }


    @PostMapping
    public ResponseEntity<Timesheet> create(@PathVariable Long projectId, @RequestBody Timesheet timesheet) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheetService.create(timesheet, projectId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long projectId, @PathVariable Long id) {
        try {
            timesheetService.delete(id, projectId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
