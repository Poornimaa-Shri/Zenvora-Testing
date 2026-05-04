package com.zenvora.zenvora_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zenvora.zenvora_backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}