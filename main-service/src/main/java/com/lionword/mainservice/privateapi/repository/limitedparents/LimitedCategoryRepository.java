package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface LimitedCategoryRepository<CategoryDto, Long> extends Repository<CategoryDto, Long> {
    CategoryDto findById(long categoryId);
}

