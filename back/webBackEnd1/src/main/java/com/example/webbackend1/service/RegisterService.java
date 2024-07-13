package com.example.webbackend1.service;

import com.example.webbackend1.entity.RegisterDTO;
import com.example.webbackend1.entity.Result;

public interface RegisterService {
    Result register(RegisterDTO registerDTO);
}
