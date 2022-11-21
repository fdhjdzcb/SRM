package com.AMIR.SRM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @GetMapping("srm/orders/new_order")
    public String new_order(Model model){
        model.addAttribute("title", "Создание заказа");
        model.addAttribute("username", "username");
        return "SRM/orders/new_order";
    }

    @GetMapping("srm/orders/current_orders")
    public String current_orders(Model model){
        model.addAttribute("title", "Текущие заказы");
        model.addAttribute("username", "username");
        return "SRM/orders/current_orders";
    }

    @GetMapping("srm/orders/completed_orders")
    public String completed_orders(Model model){
        model.addAttribute("title", "Завершенные заказы");
        model.addAttribute("username", "username");
        return "SRM/orders/completed_orders";
    }

    @GetMapping("srm/orders/canceled_orders")
    public String canceled_orders(Model model){
        model.addAttribute("title", "Отмененные заказы");
        model.addAttribute("username", "username");
        return "SRM/orders/canceled_orders";
    }
}
