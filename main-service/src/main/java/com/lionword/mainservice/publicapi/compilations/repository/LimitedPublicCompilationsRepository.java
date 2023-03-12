package com.lionword.mainservice.publicapi.compilations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPublicCompilationsRepository<T, S> extends Repository<T, S> {

    Page<T> findAllByPinned(boolean isPinned, Pageable pageable);

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(long compId);

}
