package com.example.accountservice.controller;

import com.example.accountservice.dto.*;
import com.example.accountservice.entity.Role;
import com.example.accountservice.entity.User;
import com.example.accountservice.repository.RoleRepository;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;



    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpDto signUpDto) {
        logger.info("New User is trying to sign up our application");

        // check if username or email exists
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username "+ signUpDto.getUsername()+" is already taken!",
                HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email "+ signUpDto.getEmail()+" is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create a new user, encode password
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = null;
        if (signUpDto.getUsername().contains("chuwa")) {
            roles = roleRepository.findByName("ROLE_ADMIN").get();
        } else {
            roles = roleRepository.findByName("ROLE_USER").get();
        }

        JwtSignUpResponse jwtSignUpResponse = new JwtSignUpResponse(
            "Sign up successfully: ",
            user
        );

        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);

        logger.info("User registered successfully");
        return new ResponseEntity<>(jwtSignUpResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtLoginResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        logger.info(loginDto.getUsernameOrEmail() + " is trying to sign in our application");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token from tokenProvider
        String token = tokenProvider.generateToken(authentication);
        logger.info("test generated token: "+token);
        JwtLoginResponse jwtLoginResponse =
            new JwtLoginResponse("Welcome "+loginDto.getUsernameOrEmail(), token, "JWT");

        logger.info(loginDto.getUsernameOrEmail() + " sign in successfully");
        return ResponseEntity.ok(jwtLoginResponse);
    }
}