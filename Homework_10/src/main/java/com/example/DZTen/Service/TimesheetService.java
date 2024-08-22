package com.example.DZTen.Service;

import com.example.DZTen.Aspect.Recover;
import com.example.DZTen.Aspect.Timer;
import com.example.DZTen.Repository.EmployeeRepository;
import com.example.DZTen.Repository.ProjectRepository;
import com.example.DZTen.Repository.TimesheetRepository;
import com.example.DZTen.Model.Timesheet;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Timer(level = Level.TRACE)
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;


    public TimesheetService(TimesheetService timesheetService) {
        this(timesheetService.timesheetRepository, timesheetService.projectRepository, timesheetService.employeeRepository);
    }

    @Autowired
    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }


    private Timesheet findProjectOrThrow(Long id) {
        return timesheetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project with id " + id + " does not exist"));
    }

    @Timer
    @Recover(noRecoverFor = {
            NoSuchElementException.class,
            IllegalStateException.class})
    public Optional<Timesheet> findById(Long id) {
        return Optional.ofNullable(timesheetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Timesheet with id " + id + " does not exist")));
    }


//    @Timer
//    @SneakyThrows
//    @Recover
//    public Optional<Timesheet> findById(Long id) {
////        throw new Exception();
//        throw new NoSuchElementException();
//
//        //        return timesheetRepository.findById(id);
////        System.out.println("Log inside method");
////        throw new IllegalStateException("ЧТО-ТО НЕ ТАК");
////        return Optional.of(findProjectOrThrow(id));
//    }

    //@Timer
    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        if (createdAtBefore != null && createdAtAfter != null) {
            return timesheetRepository.findByCreatedAtBetween(createdAtBefore, createdAtAfter);
        }
        return timesheetRepository.findAll();
    }

//    @Timer
    public List<Timesheet> findAllByTimesheetProjectId(Long projectId) {
        findProjectOrThrow(projectId);
        return timesheetRepository.findAllByTimesheetProjectId(projectId);
    }

    public List<Timesheet> findAllByTimesheetEmployeeId(Long employeeId) {
        findProjectOrThrow(employeeId);
        return timesheetRepository.findAllByTimesheetEmployeeId(employeeId);
    }

    @Recover
    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getTimesheetProjectId())) {
            throw new IllegalArgumentException("projectId must not be null");
        }

        if (projectRepository.findById(timesheet.getTimesheetProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getTimesheetProjectId() + " does not exists");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return timesheetRepository.save(timesheet);
    }

//    @Timer(enabled = false)
    public void delete(Long id) {
        findProjectOrThrow(id);
        timesheetRepository.deleteById(id);
    }

    public Optional<Timesheet> update(Long id, Timesheet updatedTimesheet) {
        return timesheetRepository.findById(id).map(existingTimesheet -> {
            existingTimesheet.setTimesheetProjectId(updatedTimesheet.getTimesheetProjectId());
            existingTimesheet.setTimesheetEmployeeId(updatedTimesheet.getTimesheetEmployeeId());
            existingTimesheet.setMinutes(updatedTimesheet.getMinutes());
            existingTimesheet.setCreatedAt(updatedTimesheet.getCreatedAt());
            return timesheetRepository.save(existingTimesheet);
        });
    }
}
