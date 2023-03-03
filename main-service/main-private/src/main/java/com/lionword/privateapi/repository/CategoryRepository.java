package com.lionword.privateapi.repository;

import com.lionword.entity.category.CategoryDto;
import com.lionword.privateapi.repository.limitedparents.LimitedCategoryRepository;

public interface CategoryRepository extends LimitedCategoryRepository<CategoryDto, Long> {
}
