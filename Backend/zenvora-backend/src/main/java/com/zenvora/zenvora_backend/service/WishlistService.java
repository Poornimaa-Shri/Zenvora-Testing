package com.zenvora.zenvora_backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenvora.zenvora_backend.repository.WishlistRepository;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository repo;

    public List<Integer> getWishlistIds(String username) {
        return repo.getWishlistProductIds(username);
    }

    public void addWishlist(String username, int productId) {
        repo.addItem(username, productId);
    }

    public void deleteWishlist(String username, int productId) {
        repo.deleteItem(username, productId);
    }
    
    public List<Map<String, Object>> getWishlistProducts(String username) {
        return repo.getWishlistProducts(username);
    }
}