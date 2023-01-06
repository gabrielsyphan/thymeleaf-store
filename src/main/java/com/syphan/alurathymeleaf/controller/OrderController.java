package com.syphan.alurathymeleaf.controller;
;
import com.syphan.alurathymeleaf.dto.OrderDto;
import com.syphan.alurathymeleaf.model.Order;
import com.syphan.alurathymeleaf.model.enumerations.Status;
import com.syphan.alurathymeleaf.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public OrderController(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    @GetMapping
    public String orderList(Model model) {
        Query query = this.entityManager.createQuery("SELECT o FROM Order o", Order.class);
        model.addAttribute("orders", query.getResultList());
        model.addAttribute("route", "order");
        return "order/orderList";
    }

    @GetMapping("/new")
    public String formOrder(Model model, OrderDto orderDto) {
        model.addAttribute("route", "order");
        return "order/formOrder";
    }

    @GetMapping("/status/{status}")
    public String orderListByStatus(Model model, @PathVariable("status") String status) {
        Status statusEnum = Status.valueOf(status.toUpperCase());
        Query query = this.entityManager.createQuery("SELECT o FROM Order o WHERE o.status = :status", Order.class);
        query.setParameter("status", statusEnum);
        model.addAttribute("orders", query.getResultList());
        model.addAttribute("status", statusEnum);
        return "order/orderList";
    }

    @GetMapping("/{name}")
    public String ordersByName(Model model, @PathVariable("name") String name) {
        Query query = this.entityManager.createQuery("SELECT o FROM Order o WHERE o.name LIKE '%"+ name +"%'", Order.class);
        model.addAttribute("orders", query.getResultList());
        model.addAttribute("route", "order");
        return "order/orderList";
    }

    @PostMapping
    public String save(Model model, @Valid OrderDto orderDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "order/formOrder";
        }

        model.addAttribute("route", "order");
        Status status = Status.getById(orderDto.getStatus());

        Order order = new Order();
        order.setName(orderDto.getName());
        order.setDescription(orderDto.getDescription());
        order.setPrice(orderDto.getPrice());
        order.setImageUrl(orderDto.getImageUrl());
        order.setProductUrl(orderDto.getProductUrl());
        order.setArriveDate(orderDto.getArriveDate());
        order.setStatus(status);
        this.orderRepository.save(order);

        return "redirect:/order";
    }

    @ExceptionHandler
    public String handleException(Exception exception) {
        return "redirect:/order";
    }
}
