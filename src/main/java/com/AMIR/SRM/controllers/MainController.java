package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.Role;
import com.AMIR.SRM.domain.User;
import com.AMIR.SRM.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

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

    @Autowired
    private UserRepo userRepo;

    @GetMapping("srm/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepo.findByUsername(username);
        model.addAttribute("title", "Профиль");
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("user", user);
        return "SRM/profile";
    }

    @PostMapping("srm/profile")
    public String userSave(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam("userId") User user,
            Map<String, Object> model
    ) {
        User userFromDb = userRepo.findByUsername(username);
        User mailFromDb = userRepo.findByEmail(email);
        if ((userFromDb != null) && (!Objects.equals(username, user.getUsername()))) {
            System.out.println("Данный логин уже занят!");
            model.put("message", "Данный логин уже занят!");
            return "redirect:/srm/profile/" + user.getUsername();
        } else if ((mailFromDb != null) && (!Objects.equals(email, user.getEmail()))) {
            System.out.println("Данная почта уже занята!");
            model.put("message", "Данная почта уже занята!");
            return "redirect:/srm/profile/" + user.getUsername();
        }
        else if ((Objects.equals(username, user.getUsername())) && (Objects.equals(email, user.getEmail()))){
            return "redirect:/srm/profile/" + user.getUsername();
        }
        System.out.println("Все ок");
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);

        userRepo.save(user);
        return "redirect:/login?logout";
    }
}
