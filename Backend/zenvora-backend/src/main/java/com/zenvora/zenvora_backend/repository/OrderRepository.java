package com.zenvora.zenvora_backend.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbc;

    public void placeOrder(String username) {

        String sql = """
        INSERT INTO orders(username, product_name, price, quantity, total)
        SELECT c.username, p.name, p.price, c.quantity, (p.price * c.quantity)
        FROM cart c
        JOIN products p ON c.product_id = p.id
        WHERE c.username = ?
        """;

        jdbc.update(sql, username);
    }

    public void clearCart(String username) {

        jdbc.update("DELETE FROM cart WHERE username = ?", username);
    }

    public List<Map<String, Object>> getOrders(String username) {

        String sql = "SELECT * FROM orders WHERE username=? ORDER BY order_date DESC";

        return jdbc.queryForList(sql, username);
    }
}