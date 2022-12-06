package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.News;
import com.AMIR.SRM.domain.Order;
import com.AMIR.SRM.repositories.NewsRepo;
import com.AMIR.SRM.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("srm/")
public class NewsController {
    @Autowired
    private NewsRepo newsRepo;

    @PreAuthorize("hasAnyAuthority('DIRECTOR', 'ADMIN')")
    @GetMapping("create_news")
    public String create_news(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Согласовать");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());

        return "SRM/create_news";
    }

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @PostMapping("create_news")
    public String add(
            @RequestParam String new_title,
            @RequestParam String new_text,
            Map<String, Object> model) {

        News news = new News(new_title, new_text);
        newsRepo.save(news);

        return "SRM/create_news";
    }

    @GetMapping("news_list")
    public String news_list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Согласовать");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        List<News> news = newsRepo.findAll();
        model.addAttribute("news", news);

        return "SRM/news_list";
    }
}
