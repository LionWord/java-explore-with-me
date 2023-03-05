package com.lionword.publicapi.categories.repository;

import com.lionword.entity.category.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicCategoriesRepository extends LimitedPublicCategoriesRepository<CategoryDto, Long> {
}
