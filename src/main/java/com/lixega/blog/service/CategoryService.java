package com.lixega.blog.service;

import com.lixega.blog.model.dto.request.CategoryCreationRequest;
import com.lixega.blog.model.entity.Category;
import com.lixega.blog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public Category getCategoryBySlug(String slug){
        Optional<Category> categoryOptional = categoryRepository.findBySlug(slug);
        if(categoryOptional.isEmpty()){
            String errorMessage = String.format("Category with slug %s does not exists.", slug);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }

        return categoryOptional.get();
    }

    public Category getCategoryByName(String name){
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if(categoryOptional.isEmpty()){
            String errorMessage = String.format("Category with name %s does not exists.", name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }

        return categoryOptional.get();
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
