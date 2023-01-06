package com.syphan.alurathymeleaf.controller;

import com.syphan.alurathymeleaf.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("notifications", notificationRepository.findAll());
        model.addAttribute("route", "notifications");
        return "notification/list";
    }
}
