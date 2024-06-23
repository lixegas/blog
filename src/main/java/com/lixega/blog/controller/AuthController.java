package com.lixega.blog.controller;

import com.lixega.blog.model.dto.LoginRequest;
import com.lixega.blog.model.dto.LoginResponse;
import com.lixega.blog.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public LoginResponse loginResponse(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
