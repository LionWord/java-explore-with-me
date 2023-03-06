package com.lionword.mainservice.publicapi.categories;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.publicapi.categories.service.PublicCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class PublicCategoriesController {
    private final PublicCategoriesService publicCategoriesService;

    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(name = "from") int from,
                                           @RequestParam(name = "size") int size) {
        return publicCategoriesService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable long catId) {
        return publicCategoriesService.getCategoryById(catId);
    }
}
