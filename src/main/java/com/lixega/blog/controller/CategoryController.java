package com.lixega.blog.controller;

import com.lixega.blog.model.dto.request.CategoryCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.service.ArticleService;
import com.lixega.blog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    private ArticleService articleService;

    @GetMapping("{slug}")
    public Category getCategoryBySlug(@PathVariable String slug){
        return categoryService.getCategoryBySlug(slug);
    }

    @GetMapping("{slug}/articles")
    public List<Article> getArticlesBySlugCategory(@PathVariable String slug){
        return articleService.getArticlesByCategory(slug);
    }
    @PostMapping
    public Category createCategory(@Valid @RequestBody CategoryCreationRequest categoryCreationRequest){
        return categoryService.createCategory(categoryCreationRequest);
    }



}
