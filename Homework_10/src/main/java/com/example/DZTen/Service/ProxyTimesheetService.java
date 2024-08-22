package com.example.DZTen.Service;

import com.example.DZTen.Model.Timesheet;

import java.util.Optional;

public class ProxyTimesheetService extends TimesheetService {


    private final TimesheetService timesheetService;

    public ProxyTimesheetService(TimesheetService timesheetService) {
        super(timesheetService);
        this.timesheetService = timesheetService;
    }

    @Override
    public Optional<Timesheet> findById(Long id) {
        // Before
        Optional<Timesheet> result = null;
        try {
            result = timesheetService.findById(id);
            // After Returning
        } catch (Throwable e) {
            // After Throwing
            throw e;
        } finally {
            // After
            return result;
        }

    }
}
