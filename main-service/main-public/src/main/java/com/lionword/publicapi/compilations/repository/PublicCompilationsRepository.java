package com.lionword.publicapi.compilations.repository;

import com.lionword.entity.compilation.Compilation;
import com.lionword.entity.compilation.CompilationDto;

public interface PublicCompilationsRepository extends LimitedPublicCompilationsRepository<Compilation, Long> {
}
