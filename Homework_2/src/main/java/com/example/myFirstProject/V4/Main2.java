package com.example.myFirstProject.V4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main2 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main2.class, args);
        for (int i = 1; i < 6; i++) {
            System.out.println(context.getBean(Scoreboard.class).newTicket());
            System.out.println(context.getBean(Scoreboard.class).newTicket2());
        }
    }
}