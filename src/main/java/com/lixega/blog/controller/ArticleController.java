package com.lixega.blog.controller;

import com.lixega.blog.model.dto.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/article")
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @GetMapping("/")
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("{slug}")
    public Article getArticleBySlug(@PathVariable String slug){
        return articleService.getArticleBySlug(slug);
    }

    @PostMapping("/")
    public Article createArticle(@RequestBody ArticleCreationRequest articleCreationRequest) {
        return articleService.createArticle(articleCreationRequest);
    }

    @DeleteMapping("{id}")
    public String deleteArticleById(@PathVariable Long id){
        articleService.deleteArticleById(id);
        return "Deleted successfully";
    }

}