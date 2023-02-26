package com.lionword.publicapi.categories;

import com.lionword.entity.category.CategoryDto;
import com.lionword.publicapi.categories.service.PublicCategoriesService;
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
        //stub
        return List.of();
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable long catId) {
        //stub
        return new CategoryDto();
    }
}
