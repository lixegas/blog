package com.lixega.blog.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String name;
    private String surname;
    private String username;
    private LocalDateTime birthday;
    private String password;
}
