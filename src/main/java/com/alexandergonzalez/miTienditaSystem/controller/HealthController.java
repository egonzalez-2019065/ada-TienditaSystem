package com.alexandergonzalez.miTienditaSystem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String checkApi() {
        return "<h1>The API is working ok!</h1>";
    }
}
