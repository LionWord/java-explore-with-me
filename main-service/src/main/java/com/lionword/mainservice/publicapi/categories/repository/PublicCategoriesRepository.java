package com.lionword.mainservice.publicapi.categories.repository;

import com.lionword.mainservice.entity.category.CategoryDto;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicCategoriesRepository extends LimitedPublicCategoriesRepository<CategoryDto, Long> {
}
