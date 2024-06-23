package com.lixega.blog.service;

import com.lixega.blog.model.dto.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {


    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public Article getArticleBySlug(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public Article createArticle(ArticleCreationRequest articleCreationRequest) {
        Article articleToSave = toArticle(articleCreationRequest);
        return articleRepository.save(articleToSave);
    }

    public List<Article> getArticlesByCategory(String categoryName){
        Category category = categoryService.getCategoryByName(categoryName);

        if(category == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This category does not exist.");
        }
        return articleRepository.findByCategory(category);
    }

    private Article toArticle(ArticleCreationRequest articleCreationRequest) {
        Article article = new Article();

        UserAccount userAccount = userService.getUserByNickname(articleCreationRequest.getNickname());

        if (userAccount == null || userAccount.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist.");
        }

        Article alreadyExistingArticle = getArticleBySlug(articleCreationRequest.getSlug());
        if (alreadyExistingArticle != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An article with this slug already exists.");
        }

        Category articleCategory = categoryService.getCategoryByName(articleCreationRequest.getCategory());
        if(articleCategory == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This category does not exist.");
        }

        article.setCategory(articleCategory);
        article.setTitle(articleCreationRequest.getTitle());
        article.setSlug(articleCreationRequest.getSlug());
        article.setExcerpt(articleCreationRequest.getExcerpt());
        article.setCreatedAt(LocalDateTime.now());

        return article;
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public void deleteArticleById(Long id){
        articleRepository.deleteById(id);
    }

}
