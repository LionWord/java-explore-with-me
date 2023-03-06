package com.lionword.mainservice.adminapi.categories;

import com.lionword.mainservice.adminapi.categories.service.AdminCategoriesService;
import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.category.NewCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final AdminCategoriesService adminCategoriesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addNewCategory(@RequestBody NewCategoryDto newCategory) {
        return adminCategoriesService.addNewCategory(newCategory);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long catId) {
        adminCategoriesService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@RequestBody NewCategoryDto updatedCategory, @PathVariable long catId) {
        return adminCategoriesService.updateCategory(updatedCategory, catId);
    }

}
