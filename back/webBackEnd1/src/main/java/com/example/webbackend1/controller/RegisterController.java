package com.example.webbackend1.controller;

import com.example.webbackend1.entity.RegisterDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping(value = "/register")
    @CrossOrigin // Handle CORS if frontend is on a different origin
    public Result register(@RequestBody RegisterDTO registerDTO) {
        return registerService.register(registerDTO);
    }
}
