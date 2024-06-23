package com.lixega.blog.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryCreationRequest {

    private String name;
    private String description;
    private String slug;
}
