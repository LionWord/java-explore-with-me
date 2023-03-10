package com.lionword.mainservice.publicapi.compilations;

import com.lionword.mainservice.entity.compilation.CompilationDto;
import com.lionword.mainservice.publicapi.compilations.service.PublicCompilationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class PublicCompilationsController {

    private final PublicCompilationsService publicCompilationsService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(name = "pinned", required = false) Boolean pinned,
                                                @RequestParam(name = "from", required = false, defaultValue = "0") int from,
                                                @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return publicCompilationsService.getCompilations(pinned, from, size);
    }


    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable long compId) {
        return publicCompilationsService.getCompilationById(compId);
    }
}
