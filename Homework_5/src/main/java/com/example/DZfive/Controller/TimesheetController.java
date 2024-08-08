package com.example.DZfive.Controller;

import com.example.DZfive.Model.Timesheet;
import com.example.DZfive.Service.TimesheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    // @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
    // @DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
    // @PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // /timesheets
    // /timesheets?createdAtBefore=2024-07-09
    // /timesheets?createdAtAfter=2024-07-15
    // /timesheets?createdAtAfter=2024-07-15&createdAtBefore=2024-06-05
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAllTimesheets(
            @RequestParam(required = false) LocalDate createdAtBefore,
            @RequestParam(required = false) LocalDate createdAtAfter
    ) {
        return ResponseEntity.ok(service.findAll(createdAtBefore, createdAtAfter));
    }



    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        final Timesheet created = service.create(timesheet);
        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping
    public ResponseEntity<Timesheet> update(@PathVariable Long id, @RequestBody Timesheet timesheet) {
        return service.update(id, timesheet)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }

}
