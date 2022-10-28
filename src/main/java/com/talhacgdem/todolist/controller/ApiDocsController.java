package com.talhacgdem.todolist.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiDocsController {

    @RequestMapping("/swagger")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
