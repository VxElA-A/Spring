package com.example.DZNine.Service;

import com.example.DZNine.Client.ProjectResponse;
import com.example.DZNine.Client.TimesheetResponse;
import com.example.DZNine.Pages.TimeSheetPageDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private static final Logger logger = LoggerFactory.getLogger(TimesheetPageService.class);

    @Qualifier("restTemplate")
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "http://timesheet-rest:12345";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;

    public List<TimeSheetPageDto> findAll() {
        List<TimesheetResponse> timesheets = fetchResource(
                "/timesheets",
                new ParameterizedTypeReference<List<TimesheetResponse>>() {});
        return convertTimesheetResponses(timesheets);
    }

    public Optional<TimeSheetPageDto> findById(Long id) {
        Optional<TimesheetResponse> timesheetOpt = fetchResource(
                "/timesheets/" + id,
                TimesheetResponse.class);
        return timesheetOpt.map(this::convert);
    }

    public List<TimeSheetPageDto> findAllByProjectId(Long projectId) {
        List<TimesheetResponse> timesheets = fetchResource(
                "/timesheets/project/" + projectId,
                new ParameterizedTypeReference<List<TimesheetResponse>>() {});
        return convertTimesheetResponses(timesheets);
    }

    public Optional<ProjectResponse> findProjectById(Long projectId) {
        return fetchResource("/projects/" + projectId, ProjectResponse.class);
    }

    private <T> T fetchResource(String path, ParameterizedTypeReference<T> responseType) {
        try {
            return restTemplate.exchange(
                    BASE_URL + path,
                    HttpMethod.GET,
                    null,
                    responseType).getBody();
        } catch (Exception e) {
            logger.error("Failed to fetch resource at {}", path, e);
            throw new RuntimeException("Failed to fetch resource at " + path, e);
        }
    }

    private <T> Optional<T> fetchResource(String path, Class<T> responseType) {
        try {
            T response = restTemplate.getForObject(BASE_URL + path, responseType);
            return Optional.ofNullable(response);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Failed to fetch resource at {}", path, e);
            throw new RuntimeException("Failed to fetch resource at " + path, e);
        }
    }

    private List<TimeSheetPageDto> convertTimesheetResponses(List<TimesheetResponse> timesheets) {
        List<TimeSheetPageDto> timesheetPageDtoList = new ArrayList<>();
        if (timesheets != null) {
            for (TimesheetResponse timesheet : timesheets) {
                timesheetPageDtoList.add(convert(timesheet));
            }
        }
        return timesheetPageDtoList;
    }

    private TimeSheetPageDto convert(TimesheetResponse timesheet) {
        TimeSheetPageDto timesheetPageDto = new TimeSheetPageDto();
        timesheetPageDto.setId(String.valueOf(timesheet.getTimesheetId()));
        timesheetPageDto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().format(DATE_FORMATTER));

        fetchResource("/projects/" + timesheet.getTimesheetProjectId(), ProjectResponse.class)
                .ifPresent(project -> timesheetPageDto.setProjectName(project.getProjectName()));

        return timesheetPageDto;
    }
}

//@Service
//public class TimesheetPageService {
//
//    private static final Logger logger = LoggerFactory.getLogger(TimesheetPageService.class);
//
//    @Qualifier("restTemplate")
//    private final RestTemplate restTemplate;
//
//    public TimesheetPageService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<TimeSheetPageDto> findAll() {
//        List<TimesheetResponse> timesheets;
//        try {
//            timesheets = restTemplate.exchange(
//                    "http://timesheet-rest:12345/timesheets",
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<TimesheetResponse>>() {
//                    }).getBody();
//        } catch (Exception e) {
//            logger.error("Failed to fetch timesheets", e);
//            throw new RuntimeException("Failed to fetch timesheets", e);
//        }
//
//        List<TimeSheetPageDto> timesheetPageDtoList = new ArrayList<>();
//        if (timesheets != null) {
//            for (TimesheetResponse timesheet : timesheets) {
//                timesheetPageDtoList.add(convert(timesheet));
//            }
//        }
//        return timesheetPageDtoList;
//    }
//
//    public Optional<TimeSheetPageDto> findById(Long id) {
//        try {
//            TimesheetResponse timesheet = restTemplate.getForObject(
//                    "http://timesheet-rest:12345/timesheets/" + id,
//                    TimesheetResponse.class);
//
//            if (timesheet != null) {
//                return Optional.of(convert(timesheet));
//            }
//            return Optional.empty();
//        } catch (HttpClientErrorException.NotFound e) {
//            return Optional.empty();
//        } catch (Exception e) {
//            logger.error("Failed to fetch timesheet by id {}", id, e);
//            throw new RuntimeException("Failed to fetch timesheet by id " + id, e);
//        }
//    }
//
//    public List<TimeSheetPageDto> findAllByProjectId(Long projectId) {
//        List<TimesheetResponse> timesheets;
//        try {
//            timesheets = restTemplate.exchange(
//                    "http://timesheet-rest:12345/timesheets/project/" + projectId,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<TimesheetResponse>>() {
//                    }).getBody();
//        } catch (Exception e) {
//            logger.error("Failed to fetch timesheets for project id {}", projectId, e);
//            throw new RuntimeException("Failed to fetch timesheets for project id " + projectId, e);
//        }
//
//        List<TimeSheetPageDto> timesheetPageDtoList = new ArrayList<>();
//        if (timesheets != null) {
//            for (TimesheetResponse timesheet : timesheets) {
//                timesheetPageDtoList.add(convert(timesheet));
//            }
//        }
//        return timesheetPageDtoList;
//    }
//
//    public Optional<ProjectResponse> findProjectById(Long projectId) {
//        try {
//            ProjectResponse project = restTemplate.getForObject(
//                    "http://timesheet-rest:12345/projects/" + projectId,
//                    ProjectResponse.class);
//
//            return Optional.ofNullable(project);
//        } catch (HttpClientErrorException.NotFound e) {
//            return Optional.empty();
//        } catch (Exception e) {
//            logger.error("Failed to fetch project by id {}", projectId, e);
//            throw new RuntimeException("Failed to fetch project by id " + projectId, e);
//        }
//    }
//
//    private TimeSheetPageDto convert(TimesheetResponse timesheet) {
//        TimeSheetPageDto timesheetPageDto = new TimeSheetPageDto();
//        timesheetPageDto.setId(String.valueOf(timesheet.getTimesheetId()));
//        timesheetPageDto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
//        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
//        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
//
//        try {
//            ProjectResponse project = restTemplate.getForObject(
//                    "http://timesheet-rest:12345/projects/" + timesheet.getTimesheetProjectId(),
//                    ProjectResponse.class);
//
//            if (project != null) {
//                timesheetPageDto.setProjectName(project.getProjectName());
//            }
//        } catch (Exception e) {
//            logger.error("Failed to fetch project by id {}", timesheet.getTimesheetProjectId(), e);
//        }
//        return timesheetPageDto;
//    }
//}

/**
 * The type Timesheet page service.
 */
//@Service
////@RequiredArgsConstructor
//public class TimesheetPageService {
//
//    @Qualifier("timesheetRestClient")
//    private final RestClient timesheetRestClient;
//
//
//
//    public TimesheetPageService(RestClient timesheetRestClient) {
//        this.timesheetRestClient = timesheetRestClient;
//    }
//
////private final RestClient timesheetRestClient;
//
//
////        public TimesheetPageService(
//////            DiscoveryClient discoveryClient
////    ) {
////
//////        Application application = discoveryClient.getApplication("timesheet-rest");
//////        InstanceInfo instanceInfo = application.getInstances().get(0);
//////        String url = instanceInfo.getHomePageUrl(); // http://localhost:12345
//////        String ipAdr = instanceInfo.getIPAddr(); // 127.0.0.1
////
//////        this.restClient = RestClient.create("http://localhost:12345");
////        this.timesheetRestClient = RestClient.create("http://localhost:12345");
//////        this.timesheetRestClient = RestClient.create("http://timesheet-rest:12345");
////    }
//
//
//    private static final Logger logger = LoggerFactory.getLogger(TimesheetPageService.class);
//
//    public List<TimeSheetPageDto> findAll() {
//        List<TimesheetResponse> timesheets;
//        try {
//            timesheets = timesheetRestClient
//                    .get()
//                    .uri("/timesheets")
//                    .retrieve()
//                    .body(new ParameterizedTypeReference<List<TimesheetResponse>>() {
//                    });
//        } catch (Exception e) {
//            logger.error("Failed to fetch timesheets", e);
//            throw new RuntimeException("Failed to fetch timesheets", e);
//        }
//
//        List<TimeSheetPageDto> timesheetPageDtoList = new ArrayList<>();
//
//        for (TimesheetResponse timesheet : timesheets) {
//            TimeSheetPageDto dto = new TimeSheetPageDto();
//            dto.setId(String.valueOf(timesheet.getTimesheetId()));
//            dto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
//            dto.setMinutes(String.valueOf(timesheet.getMinutes()));
//            dto.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
//
//            ProjectResponse project;
//            try {
//                project = timesheetRestClient
//                        .get()
//                        .uri("/projects/" + timesheet.getTimesheetProjectId())
//                        .retrieve()
//                        .body(ProjectResponse.class);
//            } catch (Exception e) {
//                logger.error("Failed to fetch project with id " + timesheet.getTimesheetProjectId(), e);
//                throw new RuntimeException("Failed to fetch project with id " + timesheet.getTimesheetProjectId(), e);
//            }
//
//            dto.setProjectName(project.getProjectName());
//
//            timesheetPageDtoList.add(dto);
//        }
//
//        return timesheetPageDtoList;
//    }
//
//    public Optional<TimeSheetPageDto> findById(Long id) {
//        try {
//            TimesheetResponse timesheet = timesheetRestClient
//                    .get()
//                    .uri("/timesheets/" + id)
//                    .retrieve()
//                    .body(TimesheetResponse.class);
//
//            TimeSheetPageDto dto = new TimeSheetPageDto();
//            dto.setId(String.valueOf(timesheet.getTimesheetId()));
//            dto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
//            dto.setMinutes(String.valueOf(timesheet.getMinutes()));
//            dto.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
//
//            ProjectResponse project = timesheetRestClient
//                    .get()
//                    .uri("/projects/" + timesheet.getTimesheetProjectId())
//                    .retrieve()
//                    .body(ProjectResponse.class);
//
//            dto.setProjectName(project.getProjectName());
//            return Optional.of(dto);
//        } catch (HttpClientErrorException.NotFound e) {
//            return Optional.empty();
//        }
//    }
//
//    public List<TimeSheetPageDto> findAllByProjectId(Long projectId) {
//        List<TimesheetResponse> timesheets = timesheetRestClient
//                .get()
//                .uri("/timesheets/project/" + projectId)
//                .retrieve()
//                .body(new ParameterizedTypeReference<List<TimesheetResponse>>() {
//                });
//
//        List<TimeSheetPageDto> timesheetPageDtoList = new ArrayList<>();
//
//        for (TimesheetResponse timesheet : timesheets) {
//            TimeSheetPageDto dto = new TimeSheetPageDto();
//            dto.setId(String.valueOf(timesheet.getTimesheetId()));
//            dto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
//            dto.setMinutes(String.valueOf(timesheet.getMinutes()));
//            dto.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
//
//            ProjectResponse project = timesheetRestClient
//                    .get()
//                    .uri("/projects/" + timesheet.getTimesheetProjectId())
//                    .retrieve()
//                    .body(ProjectResponse.class);
//
//            dto.setProjectName(project.getProjectName());
//            timesheetPageDtoList.add(dto);
//        }
//
//        return timesheetPageDtoList;
//    }
//
//    public Optional<ProjectResponse> findProjectById(Long projectId) {
//        try {
//            ProjectResponse project = timesheetRestClient
//                    .get()
//                    .uri("/projects/" + projectId)
//                    .retrieve()
//                    .body(ProjectResponse.class);
//            return Optional.of(project);
//        } catch (HttpClientErrorException.NotFound e) {
//            return Optional.empty();
//        }
//    }
//
//    private TimeSheetPageDto convert(TimesheetResponse timesheet) {
//        ProjectResponse project = timesheetRestClient
//                .get()
//                .uri("/projects/" + timesheet.getTimesheetProjectId())
//                .retrieve()
//                .body(ProjectResponse.class);
//
//        TimeSheetPageDto timesheetPageDto = new TimeSheetPageDto();
//        timesheetPageDto.setProjectName(project.getProjectName());
//        timesheetPageDto.setId(String.valueOf(timesheet.getTimesheetId()));
//        timesheetPageDto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
//        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
//        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
//
//        return timesheetPageDto;
//    }
//}


