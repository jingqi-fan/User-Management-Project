package com.example.webbackend1.controller;

import com.example.webbackend1.entity.LoginDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    @CrossOrigin       //后端跨域
    public Result login(@RequestBody LoginDTO loginDTO){
//        System.out.println("login");
        return loginService.login(loginDTO);
    }
}

