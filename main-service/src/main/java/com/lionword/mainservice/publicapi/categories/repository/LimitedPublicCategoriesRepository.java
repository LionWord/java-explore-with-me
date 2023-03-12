package com.lionword.mainservice.publicapi.categories.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPublicCategoriesRepository<T, S> extends Repository<T, S> {
    Page<T> findAll(Pageable pageable);

    Optional<T> findById(long catId);
}
