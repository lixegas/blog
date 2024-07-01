package com.lixega.blog.service;

import com.lixega.blog.config.JWTUtils;
import com.lixega.blog.model.dto.request.RefreshTokenRequestDTO;
import com.lixega.blog.model.dto.response.JWTResponse;
import com.lixega.blog.model.entity.RefreshToken;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.repository.RefreshTokenRepository;
import com.lixega.blog.repository.UserRepository;
import lombok.AllArgsConstructor;

import lombok.Data;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final UserService userService;

    public RefreshToken createRefreshToken(String username) {
        Optional<UserAccount> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user does not exist.");
        RefreshToken refreshToken = RefreshToken.builder()
                .userAccount(userOptional.get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
    }

    public JWTResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        Optional<RefreshToken> tokenOptional = findByToken(refreshTokenRequestDTO.getRefreshToken());
        if (tokenOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh tokenOptional provided");

        RefreshToken token = tokenOptional.get();
        refreshTokenRepository.delete(token);
        verifyExpiration(token);

        UserAccount user = token.getUserAccount();
        RefreshToken refreshTokenObj = createRefreshToken(user.getUsername());

        String refreshToken = refreshTokenObj.getToken();
        String jwt = jwtUtils.generateTokenWithUsername(user.getUsername());

        return new JWTResponse(jwt, refreshToken);
    }
}