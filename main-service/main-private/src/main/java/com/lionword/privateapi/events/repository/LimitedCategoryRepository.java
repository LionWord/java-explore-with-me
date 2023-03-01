package com.lionword.privateapi.events.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import com.lionword.entity.category.CategoryDto;
@NoRepositoryBean
public interface LimitedCategoryRepository<CategoryDto, Long> extends Repository<CategoryDto, Long> {
    CategoryDto findById(long categoryId);
}

