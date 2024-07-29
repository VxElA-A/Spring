package com.example.myFirstProject.V3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Scoreboard scoreboard(TicketNumberGenerator ticketNumberGenerator) {
        return new Scoreboard(ticketNumberGenerator);
    }
}
