package com.example.webbackend1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webbackend1.entity.RegisterDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;
    String CapitalCaptcha = "KDQU";
    String MinCaptcha = "kdqu";

    @Override
    public Result register(RegisterDTO registerDTO) {
        if (userMapper.selectByLoginName(registerDTO.getUsername()) != null) {
            return new Result(400, "Username already exists", null);
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return new Result(400, "Passwords do not match", null);
        }

        // Check if captcha is correct
        if (!CapitalCaptcha.equals(registerDTO.getCaptcha())&&(!MinCaptcha.equals(registerDTO.getCaptcha()))) {
            return new Result(400, "Invalid captcha", null);
        }

        // Assume captcha verification is done here

        User user = new User();
        user.setLoginName(registerDTO.getUsername());
        user.setUserName(registerDTO.getUsername()); // Assuming username is the same as login name
        user.setPassword(registerDTO.getPassword()); // Remember to hash the password in a real application
        user.setEmail(registerDTO.getEmail());

        if (userMapper.insert(user) > 0) {
            return new Result(200, "Registration successful", null);
        } else {
            return new Result(500, "Registration failed", null);
        }
    }
}
