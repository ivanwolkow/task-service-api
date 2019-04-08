package ru.volkov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String getSwaggerHomePage() {
        return "redirect:/swagger-ui.html";
    }
}
