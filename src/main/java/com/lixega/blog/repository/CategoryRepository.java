package com.lixega.blog.repository;

import com.lixega.blog.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findBySlug(String slug);
    Category findByName(String name);
}
