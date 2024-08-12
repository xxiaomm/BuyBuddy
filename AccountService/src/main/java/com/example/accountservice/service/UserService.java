package com.example.accountservice.service;

import com.example.accountservice.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Service
public interface UserService {
    UserDto register(UserDto userDto);
}
