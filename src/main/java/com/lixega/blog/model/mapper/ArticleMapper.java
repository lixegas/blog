package com.lixega.blog.model.mapper;

import com.lixega.blog.model.dto.request.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.model.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ArticleMapper {

    @Mapping(target = "title", source = "articleCreationRequest.title")
    @Mapping(target = "content", source = "articleCreationRequest.content")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "slug", source = "articleCreationRequest.slug")
    Article mapToArticle(ArticleCreationRequest articleCreationRequest, Category category, UserAccount author);
}