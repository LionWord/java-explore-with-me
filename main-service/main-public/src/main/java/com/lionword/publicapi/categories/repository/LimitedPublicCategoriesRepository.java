package com.lionword.publicapi.categories.repository;

import com.lionword.entity.category.CategoryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface LimitedPublicCategoriesRepository<CategoryDto, Long> extends Repository<CategoryDto, Long> {
    List<CategoryDto> findAll(Pageable pageable);
    Optional<CategoryDto> findById(long catId);
}
