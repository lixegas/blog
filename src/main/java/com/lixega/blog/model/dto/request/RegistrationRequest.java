package com.lixega.blog.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String surname;
    private String username;

    private Instant birthday;
    private String email;
    private String password;
}
