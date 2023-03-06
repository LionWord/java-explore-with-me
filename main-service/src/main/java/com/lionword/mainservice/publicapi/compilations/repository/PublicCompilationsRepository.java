package com.lionword.mainservice.publicapi.compilations.repository;

import com.lionword.mainservice.entity.compilation.Compilation;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicCompilationsRepository extends LimitedPublicCompilationsRepository<Compilation, Long> {
}
