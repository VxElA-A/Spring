package com.example.test03.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Timesheet {

    private Long id;
    private String projectId;
    private int minutes;
    private LocalDate createdAt;

}
