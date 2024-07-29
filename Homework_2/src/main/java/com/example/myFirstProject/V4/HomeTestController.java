package com.example.myFirstProject.V4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeTestController {

//    @GetMapping("/home-test")
//    public String test() {
//        return "/home-test.html";
//    }

    private final Scoreboard scoreboard;

    @Autowired
    public HomeTestController(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @GetMapping("/home-test")
    public String test(@RequestParam(value = "type", defaultValue = "regular") String type, Model model) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tickets.add(scoreboard.newTicket(type));
        }
        model.addAttribute("tickets", tickets);
        return "home-test";
    }
}
