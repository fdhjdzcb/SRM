package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.Order;
import com.AMIR.SRM.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("srm/orders/")
public class OrdersController {

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("new_order")
    public String new_order(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("title", "Создание заказа");
        model.addAttribute("username", currentPrincipalName);
        return "SRM/orders/new_order";
    }

    @DateTimeFormat(pattern="dd-mm-yyyy")
    @PostMapping("new_order")
    public String add(@RequestParam String product_name, @RequestParam String description, @RequestParam int max_price, @RequestParam int count, @RequestParam String expected_date, Map<String, Object> model){
        Order order = new Order(product_name, description, max_price, count, expected_date);
        orderRepo.save(order);

        return "SRM/orders/new_order";
    }

    @GetMapping("current_orders")
    public String current_orders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("title", "Текущие заказы");
        model.addAttribute("username", currentPrincipalName);

        List<Order> order = orderRepo.findAll();
        model.addAttribute("order", order);
        return "SRM/orders/current_orders";
    }

    @GetMapping("completed_orders")
    public String completed_orders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("title", "Завершенные заказы");
        model.addAttribute("username", currentPrincipalName);
        return "SRM/orders/completed_orders";
    }

    @GetMapping("canceled_orders")
    public String canceled_orders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("title", "Отмененные заказы");
        model.addAttribute("username", currentPrincipalName);
        return "SRM/orders/canceled_orders";
    }
}
