package com.lionword.adminapi.compilations;

import com.lionword.adminapi.compilations.service.AdminCompilationsService;
import com.lionword.entity.compilation.NewCompilationDto;
import com.lionword.entity.event.EventShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationsController {

    private final AdminCompilationsService adminCompilationsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<EventShortDto> addCompilation(@RequestBody NewCompilationDto compilation) {
        //stub
        return List.of();
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable long compId) {
        //stub
    }

    @PatchMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<EventShortDto> updateCompilation(@PathVariable long compId, @RequestBody NewCompilationDto updatedCompilation) {
        //stub
        return List.of();
    }

}
