package com.lixega.blog.model.dto.response;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private long userId;

    private String name;
    private String surname;

    private String username;
    private String email;
    private String password;

    private Instant createdAt;
    private Instant birthday;
}
