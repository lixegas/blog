package com.lixega.blog.service;

import com.lixega.blog.model.dto.CategoryCreationRequest;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public Category getCategoryBySlug(String slug){
        return categoryRepository.findBySlug(slug);
    }

    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }
    public Category createCategory(CategoryCreationRequest categoryCreationRequest){
        Category categoryToSave = toCategory(categoryCreationRequest);
        return categoryRepository.save(categoryToSave);
    }

    public Category toCategory(CategoryCreationRequest categoryCreationRequest){
        Category category = new Category();

        Category alreadyExistingCategory = getCategoryBySlug(categoryCreationRequest.getSlug());
        if(alreadyExistingCategory != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This category already exists");
        }

        category.setSlug(categoryCreationRequest.getSlug());
        category.setName(categoryCreationRequest.getName());
        category.setDescription(categoryCreationRequest.getDescription());
        return category;
    }
}
