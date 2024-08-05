package com.example.test04.contoller;

import com.example.test04.model.Timesheet;
import com.example.test04.service.TimeSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type Time sheet controller.
 */
@RestController
@RequestMapping("/projects/{projectId}/timesheets")
public class TimeSheetController {

    private final TimeSheetService timesheetService;

    /**
     * Instantiates a new Time sheet controller.
     *
     * @param timesheetService the timesheet service
     */
    public TimeSheetController(TimeSheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    /**
     * Get response entity.
     *
     * @param projectId the project id
     * @param id        the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long projectId, @PathVariable("id") Long id) {
        Optional<Timesheet> ts = timesheetService.getById(id, projectId);
        if (ts.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Gets all.
     *
     * @param projectId the project id
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(@PathVariable Long projectId) {
        return ResponseEntity.ok(timesheetService.getAllByProjectId(projectId));

    }

    /**
     * Gets all by date.
     *
     * @param projectId       the project id
     * @param createdAtAfter  the created at after
     * @param createdAtBefore the created at before
     * @return the all by date
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Timesheet>> getAllByDate(
            @PathVariable Long projectId,
            @RequestParam(required = false) String createdAtAfter,
            @RequestParam(required = false) String createdAtBefore) {

        LocalDate afterDate = createdAtAfter != null ? LocalDate.parse(createdAtAfter) : null;
        LocalDate beforeDate = createdAtBefore != null ? LocalDate.parse(createdAtBefore) : null;

        return ResponseEntity.ok(timesheetService.getAllByProjectIdAndDate(projectId, afterDate, beforeDate));
    }


    /**
     * Create response entity.
     *
     * @param projectId the project id
     * @param timesheet the timesheet
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Timesheet> create(@PathVariable Long projectId, @RequestBody Timesheet timesheet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheetService.create(timesheet, projectId));

    }

    /**
     * Delete response entity.
     *
     * @param projectId the project id
     * @param id        the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long projectId, @PathVariable Long id) {
        timesheetService.delete(id, projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
