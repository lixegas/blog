package com.lixega.blog.model.mapper;

import com.lixega.blog.model.dto.request.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.model.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;

@Mapper(imports = Instant.class)
public interface ArticleMapper {
    @Mappings({
            @Mapping(target = "title", source = "articleCreationRequest.title"),
            @Mapping(target = "content", source = "articleCreationRequest.content"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "author", source = "author"),
            @Mapping(target = "slug", source = "articleCreationRequest.slug"),
            @Mapping(target = "createdAt", expression = "java(Instant.now())")
    })
    Article mapToArticle(ArticleCreationRequest articleCreationRequest, Category category, UserAccount author);
}