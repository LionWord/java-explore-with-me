package com.lionword.mainservice.publicapi.compilations.service;

import com.lionword.mainservice.entity.compilation.CompilationDto;

import java.util.List;

public interface PublicCompilationsService {
    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);

    CompilationDto getCompilationById(long compId);
}
