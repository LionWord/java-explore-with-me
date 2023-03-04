package com.lionword.adminapi.compilations;

import com.lionword.adminapi.compilations.service.AdminCompilationsService;
import com.lionword.entity.compilation.Compilation;
import com.lionword.entity.compilation.CompilationDto;
import com.lionword.entity.compilation.NewCompilationDto;
import com.lionword.entity.compilation.UpdateCompilationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationsController {

    private final AdminCompilationsService adminCompilationsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addCompilation(@RequestBody NewCompilationDto compilation) {
        return adminCompilationsService.addCompilation(compilation);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable long compId) {
        adminCompilationsService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompilationDto updateCompilation(@PathVariable long compId, @RequestBody UpdateCompilationRequest updatedCompilation) {
        return adminCompilationsService.updateCompilation(compId, updatedCompilation);
    }

}
