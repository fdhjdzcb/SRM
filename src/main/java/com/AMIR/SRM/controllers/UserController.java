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

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        return "SRM/admin";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Изменение пользователя");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", Role.values());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("user", user);
        return "SRM/userEdit";
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

}
