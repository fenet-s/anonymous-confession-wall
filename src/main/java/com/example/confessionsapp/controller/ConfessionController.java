package com.example.confessionsapp.controller;

import com.example.confessionsapp.model.Confession;
import com.example.confessionsapp.repository.ConfessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfessionController {

    // Final field ensures it cannot be changed after the app starts
    private final ConfessionRepository confessionRepository;

    // This is "Constructor Injection" - The recommended way
    public ConfessionController(ConfessionRepository confessionRepository) {
        this.confessionRepository = confessionRepository;
    }

    @GetMapping("/")
    public String viewConfessions(Model model) {
        List<Confession> confessions = confessionRepository.findAll();
        model.addAttribute("allConfessions", confessions);
        return "index"; // Looks for src/main/resources/templates/index.html
    }
}