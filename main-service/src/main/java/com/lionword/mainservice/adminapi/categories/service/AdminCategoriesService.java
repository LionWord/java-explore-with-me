package com.lionword.mainservice.adminapi.categories.service;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.category.NewCategoryDto;

public interface AdminCategoriesService {

    CategoryDto addNewCategory(NewCategoryDto newCategory);

    void deleteCategory(long catId);

    CategoryDto updateCategory(NewCategoryDto updatedCategory, long catId);
}
