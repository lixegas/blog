package com.lixega.blog.service;

import com.lixega.blog.model.dto.request.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.model.mapper.ArticleMapper;
import com.lixega.blog.model.mapper.CategoryMapper;
import com.lixega.blog.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    private final AuthService authService;
    private final CategoryMapper categoryMapper;

    public Article getArticleBySlug(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public Article createArticle(ArticleCreationRequest articleCreationRequest) {

        Category articleCategory = categoryService.getCategoryByName(articleCreationRequest.getCategory());
        if(articleCategory == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This category does not exist.");
        }

        Article alreadyExistingArticle = getArticleBySlug(articleCreationRequest.getSlug());
        if (alreadyExistingArticle != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An article with this slug already exists.");
        }

        UserAccount userCurrent = authService.getCurrentUser();

        Article articleToSave = articleMapper.mapToArticle(articleCreationRequest,articleCategory, userCurrent);;
        return articleRepository.save(articleToSave);
    }

    public List<Article> getArticlesByCategory(String categoryName){
        Category category = categoryService.getCategoryByName(categoryName);

        if(category == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This category does not exist.");
        }
        return articleRepository.findByCategory(category);
    }


    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public void deleteArticleById(Long id){
        articleRepository.deleteById(id);
    }

}
