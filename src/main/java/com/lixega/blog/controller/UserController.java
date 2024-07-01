package com.lixega.blog.controller;

import com.lixega.blog.model.dto.UserDTO;
import com.lixega.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{username}")
        public UserDTO getUserNickname(@PathVariable String username) {
            return userService.getUserByUsername(username);
    }

    @DeleteMapping("{id}")
    public String deleteUserById(Long id){
        userService.deleteUserById(id);
        return "User deleted successfully";
    }
}
