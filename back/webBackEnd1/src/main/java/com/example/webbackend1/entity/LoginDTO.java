package com.example.webbackend1.entity;

public class LoginDTO {
    private String loginName;
    private String password;


    public LoginDTO(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public LoginDTO() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

