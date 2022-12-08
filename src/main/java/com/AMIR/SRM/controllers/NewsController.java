package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.News;
import com.AMIR.SRM.domain.Order;
import com.AMIR.SRM.repositories.NewsRepo;
import com.AMIR.SRM.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("srm/")
public class NewsController {
    @Autowired
    private NewsRepo newsRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAnyAuthority('DIRECTOR', 'ADMIN')")
    @GetMapping("create_news")
    public String create_news(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Опубликовать новость");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());

        return "SRM/create_news";
    }

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @PostMapping("create_news")
    public String add(
            @RequestParam String new_title,
            @RequestParam String new_text,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {

        News news = new News(new_title, new_text);
        if (!file.isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resulFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resulFilename));

            news.setNew_image(resulFilename);
        }
        newsRepo.save(news);

        return "SRM/create_news";
    }

    @GetMapping("news_list")
    public String news_list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Новости");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        List<News> news = newsRepo.findAll();
        model.addAttribute("news", news);

        return "SRM/news_list";
    }

    @GetMapping("news_list/{news}")
    public String watchNew(@PathVariable News news, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Новость");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("news", news);

        return "SRM/watch_new";
    }

    @GetMapping("delete_new/{news}")
    public String deleteNew(@PathVariable News news, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Новость");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("news", news);

        newsRepo.delete(news);

        return "redirect:/srm/news_list";
    }
}
