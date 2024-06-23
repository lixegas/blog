package com.lixega.blog.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private long userId;
    private LocalDateTime createdAt;

    private String name;
    private String surname;

    private String username;
    private String email;
    private String password;

    private LocalDateTime birthday;
}
