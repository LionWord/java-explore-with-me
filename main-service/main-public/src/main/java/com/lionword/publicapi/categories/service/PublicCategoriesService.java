package com.lionword.publicapi.categories.service;

import com.lionword.entity.category.CategoryDto;

import java.util.List;

public interface PublicCategoriesService {
    List<CategoryDto> getCategories(int from, int size);
    CategoryDto getCategoryById(long catId);
}
