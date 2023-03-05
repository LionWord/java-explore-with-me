package com.lionword.publicapi.categories.service;

import com.lionword.entity.category.CategoryDto;
import com.lionword.publicapi.categories.repository.PublicCategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicCategoriesServiceImpl implements PublicCategoriesService {

    private final PublicCategoriesRepository categoriesRepository;

    public List<CategoryDto> getCategories(int from, int size) {
        return categoriesRepository.findAll(PageRequest.of(from, size));
    }

    public CategoryDto getCategoryById(long catId) {
        return categoriesRepository.findById(catId).orElseThrow();
    }
}
