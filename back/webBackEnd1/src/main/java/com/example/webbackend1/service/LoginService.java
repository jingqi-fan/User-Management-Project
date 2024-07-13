package com.example.webbackend1.service;

import com.example.webbackend1.entity.LoginDTO;
import com.example.webbackend1.entity.Result;

public interface LoginService {
    public Result login(LoginDTO loginDTO);
}

