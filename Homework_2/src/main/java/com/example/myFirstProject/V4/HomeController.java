package com.example.myFirstProject.V4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Ticket> home(@RequestParam(value = "type", defaultValue = "regular") String type) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tickets.add(scoreboard.newTicket(type));
        }
        return tickets;
    }

}