package com.lionword.adminapi.categories.service;

import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.category.NewCategoryDto;

public interface AdminCategoriesService {

    CategoryDto addNewCategory(NewCategoryDto newCategory);
    void deleteCategory(long catId);
    CategoryDto updateCategory(NewCategoryDto updatedCategory, long catId);
}
