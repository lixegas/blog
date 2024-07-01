package com.lixega.blog.service;

import com.lixega.blog.model.dto.UserDTO;
import com.lixega.blog.model.entity.RefreshToken;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.model.mapper.UserMapper;
import com.lixega.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO getUserByUsername(String username) {
        Optional<UserAccount> userAccount = userRepository.findByUsername(username);
        if (userAccount.isEmpty()) {
            String errorMessage = String.format("User with username %s does not exist", username);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }

        boolean currentlyLoggedIn = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        String currentlyLoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentlyLoggedIn || !currentlyLoggedInUser.equals(username)) {
            return userMapper.mapToDTO(userAccount.get());
        }

        return userMapper.mapToAuthorizedDTO(userAccount.get());
    }

    public void deleteUserById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        Optional<UserAccount> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            String errorMessage = String.format("User with ID %s not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }

        UserAccount user = optionalUser.get();
        boolean isTheSameUser = Objects.equals(user.getUsername(), authentication.getName());
        if (!isTheSameUser) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Requesting user is not the same as the deleting account");
        }
        userRepository.deleteById(id);
    }
}
