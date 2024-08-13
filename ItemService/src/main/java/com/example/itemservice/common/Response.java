package com.example.itemservice.common;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

public class Response<T> {
    private boolean success;
    private T data;
    private String message;

    public Response(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
