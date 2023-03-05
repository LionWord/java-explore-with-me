package com.lionword.publicapi.compilations.service;

import com.lionword.entity.compilation.CompilationDto;

import java.util.List;

public interface PublicCompilationsService {
    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);
    CompilationDto getCompilationById(long compId);
}
