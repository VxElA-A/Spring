package com.example.DZNine;
/**
 *Домашнее задание
 * 1. Доделать logging-aspect: добавить настройку boolean printArgs = false.
 * Если значение true, тогда в аспекте логировать значения аргументов.
 * 2. ** Вынести RecoverAspect в стартер.
 * Добавить в его конфигурацию настройки:
 * - boolean enabled - включает\выключает работу аспекта
 * - **** List<String> noRecoverFor - список названий классов (полное имя) исключений, для которых НЕ нужно делать Recover.
 *
 */


import com.example.DZNine.Model.Project;
import com.example.DZNine.Model.Timesheet;
import com.example.DZNine.Repository.ProjectRepository;
import com.example.DZNine.Repository.TimesheetRepository;
import com.example.aspect.Logging.LoggingAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@EnableDiscoveryClient
// @EnableEurekaClient
@SpringBootApplication
//@Import(LoggingAutoConfiguration.class)
public class DZNineRestApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DZNineRestApplication.class, args);

		ConfigurableApplicationContext ctx = SpringApplication.run(DZNineRestApplication.class, args);

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);

		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setProjectName("Project #" + i);
			projectRepo.save(project);
		}

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setTimesheetProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}
	}

}
