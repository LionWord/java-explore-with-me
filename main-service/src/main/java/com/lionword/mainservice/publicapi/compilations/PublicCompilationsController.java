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
    public List<CompilationDto> getCompilations(@RequestParam(name = "pinned") Boolean pinned,
                                                @RequestParam(name = "from") int from,
                                                @RequestParam(name = "size") int size) {
        return publicCompilationsService.getCompilations(pinned, from, size);
    }


    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable long compId) {
        return publicCompilationsService.getCompilationById(compId);
    }
}
