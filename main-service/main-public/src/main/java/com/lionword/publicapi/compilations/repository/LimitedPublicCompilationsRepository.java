package com.lionword.publicapi.compilations.repository;

import com.lionword.entity.compilation.Compilation;
import com.lionword.entity.compilation.CompilationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface LimitedPublicCompilationsRepository<Compilation, Long> extends Repository<Compilation, Long> {

    List<Compilation> findAllByPinned(boolean isPinned, Pageable pageable);
    List<Compilation> findAll(Pageable pageable);
    Optional<Compilation> findById(long compId);

}
