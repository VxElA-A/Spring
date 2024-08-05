package com.example.test04.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * The type Project.
 */
@Data
@Component
public class Project {
    private Long projectId;
    private String name;
}
