package com.example.test03.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Project {
    private Long projectId;
    private String name;
}
