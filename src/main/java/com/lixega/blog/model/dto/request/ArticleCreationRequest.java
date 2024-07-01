package com.lixega.blog.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleCreationRequest {
    @NotBlank(message = "Title field is mandatory")
    private String title;
    @NotBlank(message = "Content field is mandatory")
    private String content;
    @NotBlank(message = "Slug field is mandatory")
    private String slug;
    @NotBlank(message = "Category field is mandatory")
    private String category;

    private String excerpt;
}