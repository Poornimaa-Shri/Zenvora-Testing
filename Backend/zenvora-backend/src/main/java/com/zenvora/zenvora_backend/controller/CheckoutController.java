package com.zenvora.zenvora_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zenvora.zenvora_backend.service.CheckoutService;

@RestController
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    CheckoutService service;

    @PostMapping("/place")
    public String placeCheckout(@RequestBody Map<String, Object> data) {

        String username = (String) data.get("username");

        service.moveCartToOrders(username);
        service.clearCart(username);

        return "Checkout Successful";
    }

    @GetMapping("/orders/{username}")
    public List<Map<String, Object>> getOrders(@PathVariable String username) {
        return service.getOrders(username);
    }
}