package com.lixega.blog.model.mapper;

import com.lixega.blog.model.dto.CategoryDTO;
import com.lixega.blog.model.dto.request.CategoryCreationRequest;
import com.lixega.blog.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
@Named("CategoryMapper")
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "categoryId", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "articles", ignore = true)
    })
    Category mapToCategory (CategoryCreationRequest categoryCreationRequest);

    @Named("mapToDto")
    CategoryDTO mapToDto(Category category);
}
