package com.lixega.blog.model.mapper;

import com.lixega.blog.model.dto.request.CategoryCreationRequest;
import com.lixega.blog.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    Category mapToCategory (CategoryCreationRequest categoryCreationRequest);
}
