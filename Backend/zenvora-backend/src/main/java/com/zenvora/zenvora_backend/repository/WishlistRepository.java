package com.zenvora.zenvora_backend.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<Integer> getWishlistProductIds(String username) {

        String sql = "SELECT product_id FROM wishlist WHERE username=?";

        return jdbc.query(sql,
                (rs, rowNum) -> rs.getInt("product_id"),
                username);
    }

    public void addItem(String username, int productId) {

        String check = "SELECT COUNT(*) FROM wishlist WHERE username=? AND product_id=?";
        Integer count = jdbc.queryForObject(check, Integer.class, username, productId);

        if (count == 0) {
            jdbc.update("INSERT INTO wishlist(username, product_id) VALUES(?,?)",
                    username, productId);
        }
    }

    public void deleteItem(String username, int productId) {

        jdbc.update("DELETE FROM wishlist WHERE username=? AND product_id=?",
                username, productId);
    }
    
    public List<Map<String, Object>> getWishlistProducts(String username) {

        String sql = """
        SELECT p.id as product_id, p.name, p.price, p.image
        FROM wishlist w
        JOIN products p ON w.product_id = p.id
        WHERE w.username=?
        """;

        return jdbc.queryForList(sql, username);
    }
}