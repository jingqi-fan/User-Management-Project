package com.example.webbackend1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.webbackend1.entity.LoginDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(LoginDTO loginDTO) {
        if (StringUtils.isEmpty(loginDTO.getLoginName())){
            return new Result(400,"账号不能为空","");
        }
        if (StringUtils.isEmpty(loginDTO.getPassword())){
            return new Result(400,"密码不能为空","");
        }
        //通过登录名查询用户
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginDTO.getLoginName());
        User uer=userMapper.selectOne(wrapper);

        // 检查用户是否存在
        if (uer == null) {
            return new Result(400, "该账号尚未注册", "");
        }

        //比较密码
        if (uer!=null&&uer.getPassword().equals(loginDTO.getPassword())){
            return new Result(200,"",uer);
        }
        return new Result(401,"账号或密错误","");
    }
}

