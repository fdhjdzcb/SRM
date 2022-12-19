package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.Role;
import com.AMIR.SRM.domain.User;
import com.AMIR.SRM.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("srm/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("")
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("title", "Админ-панель");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("users", userRepo.findAll());
        return "SRM/admin/admin";
    }

    @GetMapping("banned")
    public String banned(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("title", "Админ-панель");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("users", userRepo.findAll());
        return "SRM/admin/banned";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Изменение пользователя");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", Role.values());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("user", user);
        return "SRM/admin/userEdit";
    }

    @PostMapping()
    public String userSave(
            @RequestParam String role,
            @RequestParam("userId") User user
    ){
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf(role));

        userRepo.save(user);
        return "redirect:/srm/admin/";
    }

    @GetMapping("ban/{user}")
    public String banUser(@PathVariable User user, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Забан");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", Role.values());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("user", user);

        user.setActive(false);
        userRepo.save(user);
        return "redirect:/srm/admin/";
    }

    @GetMapping("unban/{user}")
    public String unbanUser(@PathVariable User user, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "разбан");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", Role.values());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("user", user);
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/srm/admin/";
    }
}
