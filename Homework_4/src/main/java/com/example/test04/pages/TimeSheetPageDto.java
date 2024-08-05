package com.example.test04.pages;

import lombok.Data;

/**
 * Класс (DTO Data Transfer Object), который описывает параметры для шаблоново HTML-страниц.
 * Т.е. он нужен для передачи параметров внутрь thymeleaf в тех контроллерах, которые сразу отдают HTML-страницы.
 */
@Data
public class TimeSheetPageDto {

    private String projectName;
    private String id;
    private String projectId;
    private String minutes;
    private String createdAt;

}
