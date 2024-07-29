package com.example.myFirstProject.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    private final Scoreboard scoreboard;

    @Autowired
    public HomeController(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @GetMapping("/home")
    public List<Ticket> home() {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i < 6; i++) {  // 1,2,3,4,5
            tickets.add(scoreboard.newTicket());
        }
        return tickets;
    }

    @GetMapping("/home2")
    public List<Ticket> home2() {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            tickets.add(scoreboard.newTicket2());  // 6,7,8,9,10
        }
        return tickets;
    }
}
