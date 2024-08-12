package com.example.accountservice.dto;

import com.example.accountservice.entity.User;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

public class JwtSignUpResponse {
    private String message;
    private User user;

    public JwtSignUpResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
