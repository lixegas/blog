package com.lixega.blog.service;

import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class SQLUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userRepository.findByUsername(username);
        if(userAccount == null || userAccount.getUserId() == null){
            String errorMessage = String.format("User with username %s not found", username);
            throw new UsernameNotFoundException(errorMessage);
        }
        return new User(userAccount.getUsername(), userAccount.getPassword(), new ArrayList<>());
    }

}