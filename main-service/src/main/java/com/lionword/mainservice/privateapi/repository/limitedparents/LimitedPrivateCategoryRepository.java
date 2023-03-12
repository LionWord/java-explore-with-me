package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateCategoryRepository<T, S> extends Repository<T, S> {
    Optional<T> findById(long categoryId);
}

