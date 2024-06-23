package com.lixega.blog.repository;

import com.lixega.blog.model.entity.Article;
import com.lixega.blog.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findBySlug(String slug);
    List<Article> findByCategory(Category category);

}
