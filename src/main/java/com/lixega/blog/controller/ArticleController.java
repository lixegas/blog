package com.lixega.blog.controller;

import com.lixega.blog.model.dto.ArticleDTO;
import com.lixega.blog.model.dto.request.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.service.ArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/article")
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @GetMapping("/")
    public List<ArticleDTO> getAllArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("{slug}")
    public ArticleDTO getArticleBySlug(@PathVariable String slug){
        return articleService.getArticleBySlug(slug);
    }

    @PostMapping("/")
    public ArticleDTO createArticle(@Valid  @RequestBody ArticleCreationRequest articleCreationRequest) {
        return articleService.createArticle(articleCreationRequest);
    }

    @DeleteMapping("{id}")
    public String deleteArticleById(@PathVariable Long id){
        articleService.deleteArticleById(id);
        return "Deleted successfully";
    }

}
