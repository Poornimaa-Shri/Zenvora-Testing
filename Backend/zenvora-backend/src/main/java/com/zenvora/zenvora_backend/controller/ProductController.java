package com.zenvora.zenvora_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zenvora.zenvora_backend.model.Product;
import com.zenvora.zenvora_backend.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts() {
        System.out.println("PRODUCT API HIT");   
        return service.getAllProducts();
    }
}