package com.lixega.blog.controller;

import com.lixega.blog.model.dto.UserCreationRequest;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("{nickname}")
    public UserAccount getUserNickname(@PathVariable String nickname) {
        return userService.getUserByNickname(nickname);
    }

    @PostMapping
    public UserAccount createUser(@RequestBody UserCreationRequest userCreationRequest) {
        return userService.createUser(userCreationRequest);
    }

    @DeleteMapping("{id}")
    public String deleteUserById(Long id){
        userService.deleteUserById(id);
        return "Deleted user successfully";
    }
}
