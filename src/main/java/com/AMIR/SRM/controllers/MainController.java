package com.AMIR.SRM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "homepage/home";
    }

    @GetMapping("/srm")
    public String srm(Model model) {
        model.addAttribute("title", "SRM");
        model.addAttribute("username", "username");
        return "SRM/SRM";
    }

    @GetMapping("srm/orders")
    public String orders(Model model){
        return "SRM/SRM";
    }

    @GetMapping("/pricelist")
    public String pricelist(Model model) {
        model.addAttribute("title", "Прайслист");
        return "homepage/pricelist";
    }

    @GetMapping("/manual")
    public String manual(Model model) {
        model.addAttribute("title", "Руководство пользователя");
        return "homepage/manual";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "homepage/about";
    }
}
