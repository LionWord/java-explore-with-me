package com.lionword.mainservice.publicapi.compilations.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface LimitedPublicCompilationsRepository<Compilation, Long> extends Repository<Compilation, Long> {

    List<Compilation> findAllByPinned(boolean isPinned, Pageable pageable);

    List<Compilation> findAll(Pageable pageable);

    Optional<Compilation> findById(long compId);

}
