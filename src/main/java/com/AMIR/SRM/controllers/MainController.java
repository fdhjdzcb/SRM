package com.AMIR.SRM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/srm")
    public String srm(Model model) {
        model.addAttribute("title", "SRM");
        return "srm";
    }

    @GetMapping("/pricelist")
    public String pricelist(Model model) {
        model.addAttribute("title", "Прайслист");
        return "pricelist";
    }

    @GetMapping("/manual")
    public String manual(Model model) {
        model.addAttribute("title", "Руководство пользователя");
        return "manual";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "about";
    }

    /*@GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Вход");
        return "login";
    }*/
}
