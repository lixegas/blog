package com.lixega.blog.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username field is mandatory")
    private String username;
    @NotBlank(message = "Password field is mandatory")
    private String password;
}
