package com.lixega.blog.controller;

import com.lixega.blog.model.dto.request.LoginRequest;
import com.lixega.blog.model.dto.request.RegistrationRequest;
import com.lixega.blog.model.dto.response.LoginResponse;
import com.lixega.blog.model.dto.response.RegistrationResponse;
import com.lixega.blog.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }


    //MODIFICATO QUI
    @PostMapping("register")
    public RegistrationResponse register(@RequestBody RegistrationRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authService.register(request);
    }
}
