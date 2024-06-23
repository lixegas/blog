package com.lixega.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleCreationRequest {

    private String title;
    private String excerpt;
    private String content;
    private String slug;
    private String category;
    private String nickname;


}
