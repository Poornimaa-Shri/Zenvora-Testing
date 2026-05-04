package com.zenvora.zenvora_backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenvora.zenvora_backend.repository.CheckoutRepository;

@Service
public class CheckoutService {

    @Autowired
    CheckoutRepository repo;

    public void moveCartToOrders(String username) {
        repo.insertCartItemsToOrders(username);
    }

    public void clearCart(String username) {
        repo.deleteCart(username);
    }

    public List<Map<String, Object>> getOrders(String username) {
        return repo.fetchOrders(username);
    }
}