package com.lixega.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private long articleId;

    private String title;
    private String excerpt;
    private String content;
    private String slug;

    private Instant createdAt;

    private UserDTO author;
    private CategoryDTO category;
}
