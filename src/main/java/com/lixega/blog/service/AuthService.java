package com.lixega.blog.service;

import com.lixega.blog.config.JWTUtils;
import com.lixega.blog.model.dto.LoginRequest;
import com.lixega.blog.model.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authenticate(authentication);

        String token = jwtUtils.generateTokenWithUsername(loginRequest.getUsername());
        return new LoginResponse(token);
    }

    public void authenticate(Authentication authentication) {

        try {
            authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
