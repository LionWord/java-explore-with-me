package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateCategoryRepository<CategoryDto, Long> extends Repository<CategoryDto, Long> {
    Optional<CategoryDto> findById(long categoryId);
}

