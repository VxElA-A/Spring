package com.example.myFirstProject.V2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyFirstProjectApplicationV2 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyFirstProjectApplicationV2.class, args);
        for (int i = 1; i < 6; i++) {
            System.out.println(context.getBean(Scoreboard.class).newTicket());
            System.out.println(context.getBean(Scoreboard.class).newTicket2());
        }
    }
}
