package com.zenvora.zenvora_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zenvora.zenvora_backend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
