package com.example.DZNine.Repository;

import com.example.DZNine.Model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {


    List<Timesheet> findAllByTimesheetEmployeeId(Long employeeId);

    List<Timesheet> findAllByTimesheetProjectId(Long employeeId);


    List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max);


//    @Query("select t from Timesheet t where t.timesheetProjectId = :projectId order by t.createdAt desc")
//    List<Timesheet> findByProjectId(Long projectId);

}
