package com.LearningSpringBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @Value("${spring.application.name}")
    String name;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", name);
        return "home";
    }
}
