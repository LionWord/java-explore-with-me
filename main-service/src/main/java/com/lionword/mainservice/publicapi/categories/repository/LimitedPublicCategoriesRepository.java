package com.lionword.mainservice.publicapi.categories.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPublicCategoriesRepository<CategoryDto, Long> extends Repository<CategoryDto, Long> {
    Page<CategoryDto> findAll(Pageable pageable);

    Optional<CategoryDto> findById(long catId);
}
