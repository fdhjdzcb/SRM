package com.AMIR.SRM.controllers;

import com.AMIR.SRM.domain.Order;
import com.AMIR.SRM.domain.PastOrder;
import com.AMIR.SRM.repositories.OrderRepo;
import com.AMIR.SRM.repositories.PastOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.AMIR.SRM.domain.Provider;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("srm/orders/")
public class OrdersController {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private PastOrderRepo pastOrderRepo;

    @GetMapping("new_order")
    public String new_order(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Создание заказа");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "SRM/orders/new_order";
    }

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @PostMapping("new_order")
    public String add(
            @RequestParam String product_name,
            @RequestParam String description,
            @RequestParam int max_price,
            @RequestParam int count,
            @RequestParam String expected_date,
            Map<String, Object> model) {

        Order order = new Order(product_name, description, max_price, count, expected_date);
        orderRepo.save(order);

        return "SRM/orders/new_order";
    }

    @GetMapping("current_orders")
    public String current_orders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Текущие заказы");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());

        List<Order> order = orderRepo.findAll();
        model.addAttribute("order", order);
        return "SRM/orders/current_orders";
    }

    @GetMapping("current_orders/{order}")
    public String orderProvider(@PathVariable Order order, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Выбор поставщика");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("order", order);

        Random random = new Random();
        int countOfProviders = random.nextInt(5) + 1;
        Provider[] providers = new Provider[countOfProviders];
        for (int i = 0; i < countOfProviders; i++) {
            int j = random.nextInt(10);
            providers[i] = new Provider();
            providers[i].setName("Поставщик " + (i + 1));
            providers[i].setNew_price((random.nextInt(10) + 91) * order.getMax_price() / 100);
            providers[i].setNew_date(order.getExpected_date());
            /*Calendar cal = Calendar.getInstance();
                LocalDate date = LocalDate.parse(order.getExpected_date());
                cal.setTime(java.sql.Date.valueOf(date));
                cal.add(Calendar.DATE, -random.nextInt(6)); //todo может быть такое что доставят раньше сегодняшнего
                order.setReal_date(cal.toString());*/
            if (j < 5) {
                providers[i].setNew_count(order.getCount()); //01234
            } else if (j > 7) {
                providers[i].setNew_count(order.getCount() * (100 - random.nextInt(49) + 1) / 100);  //89
            } else {
                providers[i].setNew_count(order.getCount() * (100 - random.nextInt(49) + 51) / 1000); //567
            }
            if (providers[i].getNew_count() == 0) providers[i].setNew_count(1);
        }//todo как-то переносить это в model и выводить
        model.addAttribute("provider", providers);

        return "SRM/orders/provider";
    }

    @PostMapping("/current_orders")
    public String providerSave(
            @RequestParam String provider_name,
            @RequestParam String[] providers_names,
            @RequestParam String[] providers_dates,
            @RequestParam int[] providers_counts,
            @RequestParam int[] providers_prices,
            @RequestParam("orderId") Order order
    ) {
        order.setProvider(provider_name);
        for (int i = 0; i < providers_names.length; i++)
        {
            if (Objects.equals(providers_names[i], provider_name))
            {
                order.setReal_price(providers_prices[i]);
                order.setReal_date(providers_dates[i]);
                order.setCount(providers_counts[i]);
                break;
            }
        }
        /*
        order.setReal_price(real_price);
        order.setReal_date(real_date);
        order.setCount(real_count);*/
        orderRepo.save(order);
        return "redirect:/srm/orders/current_orders";
    }

    @PreAuthorize("hasAnyAuthority('DIRECTOR', 'ADMIN')")
    @GetMapping("future_orders")
    public String future_orders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Согласовать");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());

        List<Order> order = orderRepo.findAll();
        model.addAttribute("order", order);
        return "SRM/orders/future_orders";
    }

    @GetMapping("future_orders/{order}")
    public String orderEditForm(@PathVariable Order order, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Согласование заказа");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("order", order);

        return "SRM/orders/approve_order";
    }

    @PostMapping("/future_orders")
    public String orderSave(
            @RequestParam String product_name,
            @RequestParam String description,
            @RequestParam int count,
            @RequestParam int max_price,
            @RequestParam String expected_date,
            @RequestParam("orderId") Order order
    ) {
        order.setProduct_name(product_name);
        order.setDescription(description);
        order.setCount(count);
        order.setMax_price(max_price);
        order.setExpected_date(expected_date);
        order.setIs_approved(true);
        Provider provider = new Provider();

        orderRepo.save(order);
        return "redirect:/srm/orders/future_orders";
    }

    @GetMapping("completed_orders")
    public String completed_orders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Завершенные заказы");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());

        List<PastOrder> pastOrder = pastOrderRepo.findAll();
        model.addAttribute("pastOrder", pastOrder);
        return "SRM/orders/completed_orders";
    }

    @GetMapping("canceled_orders")
    public String canceled_orders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Отмененные заказы");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());

        List<PastOrder> pastOrder = pastOrderRepo.findAll();
        model.addAttribute("pastOrder", pastOrder);
        return "SRM/orders/canceled_orders";
    }
}
