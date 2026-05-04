package com.zenvora.zenvora_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zenvora.zenvora_backend.model.User;
import com.zenvora.zenvora_backend.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
    UserService service;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return service.signup(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return service.login(user);
    }
}
