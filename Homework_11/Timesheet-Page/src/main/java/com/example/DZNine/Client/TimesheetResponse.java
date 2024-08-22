package com.example.DZNine.Client;

import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
public class TimesheetResponse {

    private Long timesheetId;

    private Long timesheetProjectId;

    private Long timesheetEmployeeId;

    private Integer minutes;

    private LocalDate createdAt;
}
