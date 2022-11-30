package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("title", "SRM");
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "SRM/SRM";
    }

    @GetMapping("/srm/orders")
    public String orders(Model model) {
        return "redirect:orders/new_order";
    }

    @GetMapping("/srm/orders/")
    public String orders1(Model model) {
        return "redirect:new_order";
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

    /*@GetMapping("/error")
    public String error(Model model){
        model.addAttribute("title", "Ошибка");
        return "error";
    }*/

}
