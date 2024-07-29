package com.example.myFirstProject.V4;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Data
public class Scoreboard {

    private final TicketNumberGenerator ticketNumberGenerator;

    @Autowired
    public Scoreboard(TicketNumberGenerator ticketNumberGenerator) {
        this.ticketNumberGenerator = ticketNumberGenerator;
    }

    public Ticket newTicket() {
        String ticketNumber = ticketNumberGenerator.createNewNumber();
        LocalDateTime localDateTime = LocalDateTime.now();
        return new Ticket(ticketNumber, localDateTime);
    }

    public Ticket newTicket2() {
        String ticketNumber = ticketNumberGenerator.createNewNumber2();
        LocalDateTime localDateTime = LocalDateTime.now();
        return new Ticket(ticketNumber, localDateTime);
    }

    public Ticket newTicket(String type) {
        String ticketNumber;
        if ("vip".equalsIgnoreCase(type)) {
            ticketNumber = ticketNumberGenerator.createNewNumber2();
        } else {
            ticketNumber = ticketNumberGenerator.createNewNumber();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        return new Ticket(ticketNumber, localDateTime);
    }
}
