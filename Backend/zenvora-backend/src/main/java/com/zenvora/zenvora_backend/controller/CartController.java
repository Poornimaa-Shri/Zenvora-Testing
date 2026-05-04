package com.zenvora.zenvora_backend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zenvora.zenvora_backend.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    CartService service;

    @PostMapping("/add")
    public String addToCart(@RequestBody Map<String, Object> data) {
        String username = (String) data.get("username");
        int productId = Integer.parseInt(data.get("product_id").toString());

        service.addToCart(username, productId);
        return "Added";
    }

    @PostMapping("/delete")
    public String deleteCart(@RequestBody Map<String, Object> data) {
        String username = (String) data.get("username");
        int productId = Integer.parseInt(data.get("product_id").toString());

        service.deleteCart(username, productId);
        return "Deleted";
    }

    @PostMapping("/increase")
    public String increase(@RequestBody Map<String, Object> data) {
        String username = (String) data.get("username");
        int productId = Integer.parseInt(data.get("product_id").toString());

        service.increaseQty(username, productId);
        return "Increased";
    }

    @PostMapping("/decrease")
    public String decrease(@RequestBody Map<String, Object> data) {
        String username = (String) data.get("username");
        int productId = Integer.parseInt(data.get("product_id").toString());

        service.decreaseQty(username, productId);
        return "Decreased";
    }

    @GetMapping("/{username}")
    public List<Map<String, Object>> getCart(@PathVariable String username) {
        return service.getCart(username);
    }

    @GetMapping("/count/{username}")
    public Map<String, Integer> getCount(@PathVariable String username) {
        return Map.of("count", service.getCartCount(username));
    }

    @GetMapping("/exists/{username}")
    public List<Integer> getExists(@PathVariable String username) {
        return service.getCartProductIds(username);
    }
}