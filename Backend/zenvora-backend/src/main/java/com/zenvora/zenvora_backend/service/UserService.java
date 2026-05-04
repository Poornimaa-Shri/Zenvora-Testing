package com.zenvora.zenvora_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenvora.zenvora_backend.model.User;
import com.zenvora.zenvora_backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    UserRepository repo;

    public String signup(User user) {

        if (repo.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }

        repo.save(user);
        return "Signup successful";
    }

    public boolean login(User user) {

        return repo.findByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        ) != null;
    }
}
