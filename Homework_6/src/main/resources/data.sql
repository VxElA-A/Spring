
-- Вставка начальных данных в таблицу Employee
INSERT INTO employee (employee_name) VALUES ('John1');
INSERT INTO employee (employee_name) VALUES ('John2');
INSERT INTO employee (employee_name) VALUES ('John3');

-- Вставка начальных данных в таблицу Project
INSERT INTO project (project_name) VALUES ('Spring1');
INSERT INTO project (project_name) VALUES ('Spring2');
INSERT INTO project (project_name) VALUES ('Spring3');

-- Вставка начальных данных в таблицу Employee_Project
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 1);
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 2);
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 3);
INSERT INTO employee_project (employee_id, project_id) VALUES (2, 2);
INSERT INTO employee_project (employee_id, project_id) VALUES (3, 3);


-- Вставка начальных данных в таблицу Timesheet
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (1, 1, 800, '2022-01-01');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (2, 1, 200, '2022-01-02');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (2, 2, 300, '2022-01-03');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (3, 2, 500, '2022-01-04');
INSERT INTO timesheet (timesheet_project_id, timesheet_employee_id, minutes, created_at) VALUES (3, 3, 70, '2022-01-05');

--INSERT INTO timesheet (project_id, minutes) VALUES (1, 800);
--INSERT INTO timesheet (project_id, minutes) VALUES (2, 200);
--INSERT INTO timesheet (project_id, minutes) VALUES (2, 300);
--INSERT INTO timesheet (project_id, minutes) VALUES (3, 500);
--INSERT INTO timesheet (project_id, minutes) VALUES (3, 70);