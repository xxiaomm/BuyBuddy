package com.example.accountservice.dto;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

public class JwtLoginResponse {
    private String message;
    private String token;
    private String tokenType = "JWT";

    public JwtLoginResponse(String message, String token, String tokenType) {
        this.message = message;
        this.token = token;
        this.tokenType = tokenType;
    }

    public JwtLoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
