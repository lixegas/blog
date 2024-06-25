package com.lixega.blog.service;

import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserAccount getUserByNickname(String nickname) {
        return userRepository.findByUsername(nickname);
    }



    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
