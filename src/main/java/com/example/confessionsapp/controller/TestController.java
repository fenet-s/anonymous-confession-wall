package com.example.confessionsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/ping")
    public String ping() {
        return "Backend is working! Time: " + java.time.LocalDateTime.now();
    }
}