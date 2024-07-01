package com.lixega.blog.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryCreationRequest  {
    @NotBlank(message = "Name field is mandatory")
    private String name;
    private String description;
    @NotBlank(message = "Slug field is mandatory")
    private String slug;
}
