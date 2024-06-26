package com.lixega.blog.model.mapper;

import com.lixega.blog.model.dto.ArticleDTO;
import com.lixega.blog.model.dto.request.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.model.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.Instant;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, CategoryMapper.class},
        imports = {Instant.class}
)
@Named("ArticleMapper")
public interface ArticleMapper {
    @Mappings({
            @Mapping(target = "articleId", ignore = true),
            @Mapping(target = "title", source = "articleCreationRequest.title"),
            @Mapping(target = "content", source = "articleCreationRequest.content"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "author", source = "author"),
            @Mapping(target = "slug", source = "articleCreationRequest.slug"),
            @Mapping(target = "createdAt", expression = "java(Instant.now())")
    })
    Article mapToArticle(ArticleCreationRequest articleCreationRequest, Category category, UserAccount author);

    @Mappings({
        @Mapping(target = "author", qualifiedByName = { "UserMapper", "mapToDto" }),
        @Mapping(target = "category", qualifiedByName = { "CategoryMapper", "mapToDto"})
    })
    ArticleDTO mapToDto(Article article);
}