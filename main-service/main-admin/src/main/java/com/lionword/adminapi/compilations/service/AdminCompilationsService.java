package com.lionword.adminapi.compilations.service;

import com.lionword.entity.compilation.CompilationDto;
import com.lionword.entity.compilation.NewCompilationDto;
import com.lionword.entity.compilation.UpdateCompilationRequest;

public interface AdminCompilationsService {
    CompilationDto addCompilation(NewCompilationDto compilation);
    void deleteCompilation(long compId);
    CompilationDto updateCompilation(long compId, UpdateCompilationRequest updatedCompilation);
}
