package com.example.myFirstProject.V1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Data
public class Scoreboard {

    //    @Autowired
    private final TicketNumberGenerator ticketNumberGenerator;

    @Autowired
    public Scoreboard(TicketNumberGenerator ticketNumberGenerator) {
        this.ticketNumberGenerator = ticketNumberGenerator;
    }


    public Ticket newTicket() {
        String ticketNumber = ticketNumberGenerator.createNewNumber();
        LocalDateTime localDateTime = LocalDateTime.now();
        Ticket ticket = new Ticket(ticketNumber, localDateTime);
        return ticket;
    }

    public Ticket newTicket2() {
        String ticketNumber = ticketNumberGenerator.createNewNumber2();
        LocalDateTime localDateTime = LocalDateTime.now();
        Ticket ticket = new Ticket(ticketNumber, localDateTime);
        return ticket;
    }
}
