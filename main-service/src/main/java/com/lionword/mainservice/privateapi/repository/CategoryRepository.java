package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedCategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends LimitedCategoryRepository<CategoryDto, Long> {
}
