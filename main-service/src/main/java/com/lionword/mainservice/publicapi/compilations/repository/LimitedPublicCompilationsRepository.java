package com.lionword.mainservice.publicapi.compilations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface LimitedPublicCompilationsRepository<Compilation, Long> extends Repository<Compilation, Long> {

    Page<Compilation> findAllByPinned(boolean isPinned, Pageable pageable);

    Page<Compilation> findAll(Pageable pageable);

    Optional<Compilation> findById(long compId);

}
