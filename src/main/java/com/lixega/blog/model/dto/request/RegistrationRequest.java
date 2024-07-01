package com.lixega.blog.model.dto.request;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Email field is mandatory")
    private String email;
    @NotBlank(message = "Password field is mandatory")
    private String password;
    @NotBlank(message = "Username field is mandatory")
    private String username;

    private Instant birthday;

}
