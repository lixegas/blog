package com.lixega.blog.service;

import com.lixega.blog.config.JWTUtils;
import com.lixega.blog.model.dto.request.LoginRequest;
import com.lixega.blog.model.dto.request.RegistrationRequest;
import com.lixega.blog.model.dto.response.LoginResponse;
import com.lixega.blog.model.dto.response.RegistrationResponse;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.repository.UserRepository;
import io.jsonwebtoken.security.Password;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;


    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authenticate(authentication);

        String token = jwtUtils.generateTokenWithUsername(loginRequest.getUsername());
        return new LoginResponse(token);
    }

    public RegistrationResponse register(RegistrationRequest request){
        UserAccount userAccount = createUser(request);

        UserAccount registeredUserAccount = userRepository.save(userAccount);
        return toRegistrationResponse(registeredUserAccount);
    }

    public UserAccount createUser(RegistrationRequest registrationRequest) {
        UserAccount userAccountToSave = toUser(registrationRequest);
        return userRepository.save(userAccountToSave);
    }

    private UserAccount toUser(RegistrationRequest registrationRequest) {
        UserAccount userAccount = new UserAccount();

        userAccount.setName(registrationRequest.getName());
        userAccount.setSurname(registrationRequest.getSurname());
        userAccount.setUsername(registrationRequest.getUsername());
        userAccount.setBirthday(registrationRequest.getBirthday());
        userAccount.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return userAccount;
    }

    private RegistrationResponse toRegistrationResponse(UserAccount userAccount){
        RegistrationResponse response = new RegistrationResponse();
        response.setSurname(userAccount.getSurname());
        response.setName(userAccount.getName());
        response.setUsername(userAccount.getUsername());
        response.setPassword(userAccount.getPassword());
        response.setEmail(userAccount.getEmail());
        response.setCreatedAt(userAccount.getCreateAt());
        response.setBirthday(userAccount.getBirthday());

        return response;
    }

    public void authenticate(Authentication authentication) {

        try {
            authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
