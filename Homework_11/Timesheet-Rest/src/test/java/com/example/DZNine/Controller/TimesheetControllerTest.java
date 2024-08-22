package com.example.DZNine.Controller;


// JUnit 5.7 - фреймворк для тестирования
// Mockito - библиотека для мокинга объектов (методов, классов)
// Assertj - библиотека для проверки истинности условий (ассертов)


import com.example.DZNine.Model.Timesheet;
import com.example.DZNine.Service.TimesheetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TimesheetController.class)
class TimesheetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimesheetService timesheetService;

    @Test
    void testGetTimesheetById_Found() throws Exception {
        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetId(1L);
        Mockito.when(timesheetService.findById(1L)).thenReturn(Optional.of(timesheet));

        mockMvc.perform(get("/timesheets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timesheetId").value(1L));
    }

    @Test
    void testGetTimesheetById_NotFound() throws Exception {
        Mockito.when(timesheetService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/timesheets/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testGetAllTimesheets() throws Exception {
        Timesheet timesheet1 = new Timesheet();
        Timesheet timesheet2 = new Timesheet();
        List<Timesheet> timesheets = List.of(timesheet1, timesheet2);
        Mockito.when(timesheetService.findAll(null, null)).thenReturn(timesheets);

        mockMvc.perform(get("/timesheets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testCreateTimesheet() throws Exception {
        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetId(1L);
        Mockito.when(timesheetService.create(Mockito.any(Timesheet.class))).thenReturn(timesheet);

        mockMvc.perform(post("/timesheets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.timesheetId").value(1L));
    }

    @Test
    void testUpdateTimesheet_Found() throws Exception {
        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetId(1L);
        Mockito.when(timesheetService.update(Mockito.eq(1L), Mockito.any(Timesheet.class))).thenReturn(Optional.of(timesheet));

        mockMvc.perform(put("/timesheets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timesheetId").value(1L));
    }

    @Test
    void testUpdateTimesheet_NotFound() throws Exception {
        Mockito.when(timesheetService.update(Mockito.eq(1L), Mockito.any(Timesheet.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/timesheets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testDeleteTimesheet() throws Exception {
        mockMvc.perform(delete("/timesheets/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(timesheetService).delete(1L);
    }
}

