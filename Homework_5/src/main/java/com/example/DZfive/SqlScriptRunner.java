package com.example.DZfive;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//@Component
//public class SqlScriptRunner {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    public void runScripts() {
//        try (Connection connection = dataSource.getConnection()) {
//            ScriptUtils.executeSqlScript(connection, new ClassPathResource("schema.sql"));
//            ScriptUtils.executeSqlScript(connection, new ClassPathResource("data.sql"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
