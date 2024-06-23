package com.lixega.blog.service;

import com.lixega.blog.model.dto.UserCreationRequest;
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

    public UserAccount createUser(UserCreationRequest userCreationRequest) {
        UserAccount userAccountToSave = toUser(userCreationRequest);
        return userRepository.save(userAccountToSave);
    }

    public UserAccount toUser(UserCreationRequest userCreationRequest) {
        UserAccount userAccount = new UserAccount();

        userAccount.setName(userCreationRequest.getName());
        userAccount.setSurname(userCreationRequest.getSurname());
        userAccount.setUsername(userCreationRequest.getUsername());
        userAccount.setBirthday(userCreationRequest.getBirthday());
        return userAccount;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
