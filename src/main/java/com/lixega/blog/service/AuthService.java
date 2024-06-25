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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final UserMapper userMapper;


    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authenticate(authentication);

        String token = jwtUtils.generateTokenWithUsername(loginRequest.getUsername());
        return new LoginResponse(token);
    }

    public UserAccount getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserAccount currentUser = userRepository.findByUsername(username);
        if (currentUser == null) throw new UsernameNotFoundException("User not found");
        return currentUser;
    }


    public RegistrationResponse register(RegistrationRequest request) {
        try {
            // Creazione dell'account utente
            UserAccount userAccount = createUser(request);
            // Salvataggio dell'account utente nel repository
            UserAccount registeredUserAccount = userRepository.save(userAccount);
            return userMapper.mapToResponse(registeredUserAccount);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore durante la registrazione dell'utente");
        }
    }


    public UserAccount createUser(RegistrationRequest registrationRequest) {
        UserAccount userAccountToSave = userMapper.mapToUser(registrationRequest);
        return userRepository.save(userAccountToSave);
    }


    public void authenticate(Authentication authentication) {

        try {
            authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
