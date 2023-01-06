package com.syphan.alurathymeleaf.controller;

import com.syphan.alurathymeleaf.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final OrderRepository orderRepository;

    @Autowired
    public HomeController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("route", "home");
        model.addAttribute("orders", orderRepository.findAll(PageRequest.of(0,6)));
        return "home/home";
    }
}
