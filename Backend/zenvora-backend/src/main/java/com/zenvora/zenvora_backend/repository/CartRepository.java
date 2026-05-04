package com.zenvora.zenvora_backend.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    @Autowired
    JdbcTemplate jdbc;

    public void addItem(String username, int productId) {
        String check = "SELECT COUNT(*) FROM cart WHERE username=? AND product_id=?";
        Integer count = jdbc.queryForObject(check, Integer.class, username, productId);

        if (count != null && count > 0) {
            jdbc.update("UPDATE cart SET quantity = quantity + 1 WHERE username=? AND product_id=?",
                    username, productId);
        } else {
            jdbc.update("INSERT INTO cart(username, product_id, quantity) VALUES(?,?,1)",
                    username, productId);
        }
    }

    public void deleteItem(String username, int productId) {
        jdbc.update("DELETE FROM cart WHERE username=? AND product_id=?",
                username, productId);
    }

    public void increaseQty(String username, int productId) {
        jdbc.update("UPDATE cart SET quantity = quantity + 1 WHERE username=? AND product_id=?",
                username, productId);
    }

    public void decreaseQty(String username, int productId) {

        String sql = "SELECT quantity FROM cart WHERE username=? AND product_id=?";
        Integer qty = jdbc.queryForObject(sql, Integer.class, username, productId);

        if (qty == null) return;

        if (qty == 1) {
            jdbc.update("DELETE FROM cart WHERE username=? AND product_id=?",
                    username, productId);
        }
        else {
            jdbc.update("UPDATE cart SET quantity = quantity - 1 WHERE username=? AND product_id=?",
                    username, productId);
        }
    }

    public List<Map<String, Object>> getCartItems(String username) {

        String sql = """
        SELECT p.id, p.name, p.price, p.image, c.quantity
        FROM cart c
        JOIN products p ON c.product_id = p.id
        WHERE c.username=?
        """;

        return jdbc.queryForList(sql, username);
    }

    public int getCartCount(String username) {
        String sql = "SELECT SUM(quantity) FROM cart WHERE username=?";
        Integer count = jdbc.queryForObject(sql, Integer.class, username);
        return count == null ? 0 : count;
    }

    public List<Integer> getProductIds(String username) {
        String sql = "SELECT product_id FROM cart WHERE username=?";
        return jdbc.query(sql, (rs, rowNum) -> rs.getInt("product_id"), username);
    }
}