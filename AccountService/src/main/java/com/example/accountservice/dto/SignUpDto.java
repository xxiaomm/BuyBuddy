package com.example.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

public class SignUpDto {
    @JsonProperty(value = "username")
    private String username;
    private String email;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
