package com.example.myFirstProject.V4;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TicketNumberGenerator {

    public String createNewNumber() {
        String uuid = UUID.randomUUID().toString();
        return "Ticket #" + uuid;
    }

    public String createNewNumber2() {
        String uuid = UUID.randomUUID().toString();
        return "VIP Ticket #" + uuid;
    }
}
