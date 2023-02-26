package com.lionword.publicapi.compilations;

import com.lionword.entity.compilation.CompilationDto;
import com.lionword.entity.compilation.NewCompilationDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.publicapi.compilations.service.PublicCompilationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class PublicCompilationsController {

    private final PublicCompilationsService publicCompilationsService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(name = "pinned") boolean pinned,
                                                @RequestParam(name = "from") int from,
                                                @RequestParam(name = "size") int size) {
        //stub
        return List.of();
    }


    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable long compId) {
        //stub
        return new CompilationDto();
    }
}
