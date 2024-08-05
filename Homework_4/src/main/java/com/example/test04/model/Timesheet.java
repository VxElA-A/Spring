package com.example.test04.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * The type Timesheet.
 */
@Data
public class Timesheet {

    private Long id;
    private String projectId;
    private int minutes;
    private LocalDate createdAt;

}
