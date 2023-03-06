package com.lionword.mainservice.adminapi.categories.repository;

import com.lionword.mainservice.entity.category.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminCategoriesRepository extends JpaRepository<CategoryDto, Long> {
    Optional<CategoryDto> findByName(String name);
}
