package com.lixega.blog.service;

import com.lixega.blog.config.JWTUtils;
import com.lixega.blog.model.dto.request.LoginRequest;
import com.lixega.blog.model.dto.request.RegistrationRequest;
import com.lixega.blog.model.dto.response.LoginResponse;
import com.lixega.blog.model.dto.response.RegistrationResponse;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.model.mapper.UserMapper;
import com.lixega.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authenticationManager.authenticate(authentication);

        String token = jwtUtils.generateTokenWithUsername(loginRequest.getUsername());
        return new LoginResponse(token);
    }

    public UserAccount getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserAccount> currentUser = userRepository.findByUsername(username);

        if (currentUser.isEmpty()) throw new UsernameNotFoundException("User not found");
        return currentUser.get();
    }


    public RegistrationResponse register(RegistrationRequest request) {
        checkForValidEmail(request.getEmail());
        checkForValidPassword(request.getPassword());

        checkForUserWithSameUsername(request.getUsername());
        checkForUserWithSameEmail(request.getEmail());

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserAccount registeredUserAccount = userRepository.save(userMapper.mapToUser(request));

        return userMapper.mapToRegistrationResponse(registeredUserAccount);
    }

    private void checkForUserWithSameUsername(String username){
        boolean userWithSameUsernameExists = userRepository.findByUsername(username).isPresent();
        if(userWithSameUsernameExists) {
            String errorMessage = String.format("User with username %s already exists", username);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }

    private void checkForUserWithSameEmail(String email){
        boolean userWithSameUsernameExists = userRepository.findByUsername(email).isPresent();
        if(userWithSameUsernameExists) {
            String errorMessage = String.format("User with email %s already exists", email);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }

    private void checkForValidEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email provided");
        }
    }

    private void checkForValidPassword(String password){
        if(password.isBlank() || password.length() < 6){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 6 characters long.");
        }
    }

}
