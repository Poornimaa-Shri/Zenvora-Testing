package com.zenvora.zenvora_backend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zenvora.zenvora_backend.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin
public class WishlistController {

    @Autowired
    WishlistService service;

    @GetMapping("/exists/{username}")
    public List<Integer> getWishlist(@PathVariable String username) {
        return service.getWishlistIds(username);
    }

    @PostMapping("/add")
    public String add(@RequestBody Map<String, Object> data) {

        String username = (String) data.get("username");
        int productId = Integer.parseInt(data.get("product_id").toString());

        service.addWishlist(username, productId);

        return "added";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Map<String, Object> data) {

        String username = (String) data.get("username");
        int productId = Integer.parseInt(data.get("product_id").toString());

        service.deleteWishlist(username, productId);

        return "deleted";
    }
    
    @GetMapping("/{username}")
    public List<Map<String, Object>> getWishlistFull(@PathVariable String username) {
        return service.getWishlistProducts(username);
    }
}