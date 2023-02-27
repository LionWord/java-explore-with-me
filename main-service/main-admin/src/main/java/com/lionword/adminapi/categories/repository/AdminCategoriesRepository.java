package com.lionword.adminapi.categories.repository;

import com.lionword.entity.category.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCategoriesRepository extends JpaRepository<CategoryDto, Long> {
}
