package com.example.myFirstProject.V3;

import java.time.LocalDateTime;

public class Scoreboard {

    private final TicketNumberGenerator ticketNumberGenerator;

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
}
