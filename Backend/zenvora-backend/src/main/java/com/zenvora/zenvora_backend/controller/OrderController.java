package com.zenvora.zenvora_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zenvora.zenvora_backend.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping("/place")
    public String placeOrder(@RequestBody Map<String, String> data) {

        String username = data.get("username");

        service.placeOrder(username);

        return "order placed";
    }

    @GetMapping("/{username}")
    public List<Map<String, Object>> getOrders(@PathVariable String username) {

        return service.getOrders(username);
    }
}