package com.example.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @JsonProperty(value = "username")
    private String username;
    private String email;
    private String password;

}
