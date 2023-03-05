package com.lionword.adminapi.categories.repository;

import com.lionword.entity.category.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminCategoriesRepository extends JpaRepository<CategoryDto, Long> {
    Optional<CategoryDto> findByName(String name);
}
