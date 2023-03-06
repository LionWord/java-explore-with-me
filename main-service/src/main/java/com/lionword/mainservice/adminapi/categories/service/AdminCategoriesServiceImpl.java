package com.lionword.mainservice.adminapi.categories.service;

import com.lionword.mainservice.adminapi.categories.repository.AdminCategoriesRepository;
import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.category.NewCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final AdminCategoriesRepository categoriesRepository;

    public CategoryDto addNewCategory(NewCategoryDto newCategory) {
        if (categoriesRepository.findByName(newCategory.getName()).isPresent()) {
            //stub (name must be unique)
            throw new RuntimeException();
        }
        CategoryDto category = new CategoryDto();
        category.setName(newCategory.getName());
        return categoriesRepository.save(category);
    }

    public void deleteCategory(long catId) {
        categoriesRepository.deleteById(catId);
    }

    public CategoryDto updateCategory(NewCategoryDto updatedCategory, long catId) {
        if (categoriesRepository.findByName(updatedCategory.getName()).isPresent()) {
            //stub (name must be unique)
            throw new RuntimeException();
        }
        CategoryDto category = categoriesRepository.findById(catId).orElseThrow();
        category.setName(updatedCategory.getName());
        return categoriesRepository.save(category);
    }

}
