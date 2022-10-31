package com.AMIR.SRM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SRMController {
    @GetMapping("/srm/application")
    public String first(Model model) {
        model.addAttribute("title", "Приложение");
        return "application";
    }
}
