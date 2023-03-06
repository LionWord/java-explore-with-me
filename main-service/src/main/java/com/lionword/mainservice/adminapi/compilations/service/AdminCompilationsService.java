package com.lionword.mainservice.adminapi.compilations.service;

import com.lionword.mainservice.entity.compilation.CompilationDto;
import com.lionword.mainservice.entity.compilation.NewCompilationDto;
import com.lionword.mainservice.entity.compilation.UpdateCompilationRequest;

public interface AdminCompilationsService {
    CompilationDto addCompilation(NewCompilationDto compilation);

    void deleteCompilation(long compId);

    CompilationDto updateCompilation(long compId, UpdateCompilationRequest updatedCompilation);
}
