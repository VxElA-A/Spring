package com.example.myFirstProject.V1;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class TicketNumberGenerator {

    private int counter = 1;

    public String createNewNumber() {
        String uuid = UUID.randomUUID().toString();
        return "Ticket #" + uuid;
    }

    public String createNewNumber2() {
        return "Ticket #" + counter++;
    }

}
