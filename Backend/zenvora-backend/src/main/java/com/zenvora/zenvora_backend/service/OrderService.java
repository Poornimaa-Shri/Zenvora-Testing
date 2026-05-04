package com.zenvora.zenvora_backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenvora.zenvora_backend.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository repo;

    public void placeOrder(String username) {
        repo.placeOrder(username);
        repo.clearCart(username);
    }

    public List<Map<String, Object>> getOrders(String username) {
        return repo.getOrders(username);
    }
}