package com.zenvora.zenvora_backend.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenvora.zenvora_backend.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository repo;

    public void addToCart(String username, int productId) {
        repo.addItem(username, productId);
    }

    public void deleteCart(String username, int productId) {
        repo.deleteItem(username, productId);
    }

    public void increaseQty(String username, int productId) {
        repo.increaseQty(username, productId);
    }

    public void decreaseQty(String username, int productId) {
        repo.decreaseQty(username, productId);
    }

    public List<Map<String, Object>> getCart(String username) {
        return repo.getCartItems(username);
    }

    public int getCartCount(String username) {
        return repo.getCartCount(username);
    }

    public List<Integer> getCartProductIds(String username) {
        return repo.getProductIds(username);
    }
}