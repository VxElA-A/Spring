
-- Удаление таблиц, если они существуют
DROP TABLE IF EXISTS employee_project CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS timesheet CASCADE;

-- Создание таблицы Employee
CREATE TABLE employee (
                          employee_id SERIAL PRIMARY KEY,
                          employee_name VARCHAR(100)
);

-- Создание таблицы Project
CREATE TABLE project (
                         project_id SERIAL PRIMARY KEY,
                         project_name VARCHAR(100)
);
-- Создание таблицы Employee_Project для связи Employee и Project
CREATE TABLE employee_project (
                                  employee_project_id SERIAL PRIMARY KEY,
                                  employee_id INT,
                                  project_id INT,
                                  FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),
                                  FOREIGN KEY (project_id) REFERENCES Project(project_id)
);

-- Создание таблицы Timesheet
CREATE TABLE timesheet (
                           timesheet_id SERIAL PRIMARY KEY,
                           timesheet_project_id BIGINT,
                           timesheet_employee_id BIGINT,
                           minutes INTEGER,
                           created_at DATE
);

--CREATE TABLE timesheet (
--                         timesheet_id SERIAL PRIMARY KEY,
--                         project_id INT,
--                         minutes INT
--);