package com.lionword.mainservice.publicapi.categories.service;

import com.lionword.mainservice.entity.category.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublicCategoriesService {
    List<CategoryDto> getCategories(int from, int size);

    CategoryDto getCategoryById(long catId);
}
