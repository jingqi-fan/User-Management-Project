package com.example.webbackend1.controller;

import com.example.webbackend1.entity.User;
import com.example.webbackend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
