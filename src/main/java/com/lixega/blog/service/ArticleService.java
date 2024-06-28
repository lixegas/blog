package com.lixega.blog.service;

import com.lixega.blog.model.dto.ArticleDTO;
import com.lixega.blog.model.dto.request.ArticleCreationRequest;
import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.model.entity.UserAccount;
import com.lixega.blog.model.mapper.ArticleMapper;
import com.lixega.blog.model.mapper.CategoryMapper;
import com.lixega.blog.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    private final AuthService authService;

    public ArticleDTO getArticleBySlug(String slug) {
        Optional<Article> optionalArticle = articleRepository.findBySlug(slug);
        if (optionalArticle.isEmpty()) {
            String errorMessage = String.format("Article with slug %s not found", slug);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }
        return articleMapper.mapToDto(optionalArticle.get());
    }

    public ArticleDTO createArticle(ArticleCreationRequest articleCreationRequest) {
        Category articleCategory = categoryService.getCategoryByName(articleCreationRequest.getCategory());

        Optional<Article> articleWithSameSlug = articleRepository.findBySlug(articleCreationRequest.getSlug());
        if (articleWithSameSlug.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An article with this slug already exists.");
        }

        UserAccount userCurrent = authService.getCurrentUser();

        Article articleToSave = articleMapper.mapToArticle(articleCreationRequest, articleCategory, userCurrent);
        return articleMapper.mapToDto(articleRepository.save(articleToSave));
    }

    public List<Article> getArticlesByCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This category does not exist.");
        }
        return articleRepository.findByCategory(category);
    }

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();

        return articles.parallelStream()
                .map(articleMapper::mapToDto).toList();
    }

    public void deleteArticleById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            String errorMessage = String.format("Couldn't find article with id %s", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }

        Article articleToDelete = optionalArticle.get();
        String currentlyLoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isSameCreator = Objects.equals(articleToDelete.getAuthor().getUsername(), currentlyLoggedInUser);
        if(!isSameCreator){
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request is not coming from original creator");
        }

        articleRepository.deleteById(id);
    }

}
