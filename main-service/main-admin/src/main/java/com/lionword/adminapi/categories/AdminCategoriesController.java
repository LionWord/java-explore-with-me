package com.lionword.adminapi.categories;

import com.lionword.adminapi.categories.service.AdminCategoriesService;
import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.category.NewCategoryDto;
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
        //stub
        //unique category name
        return new CategoryDto();
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long catId) {
        //stub
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@RequestBody NewCategoryDto updatedCategory, @PathVariable long catId) {
        //stub
        //unique category name
        return new CategoryDto();
    }

}
