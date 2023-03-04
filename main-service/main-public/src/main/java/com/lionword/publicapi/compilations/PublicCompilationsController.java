package com.lionword.publicapi.compilations;

import com.lionword.entity.compilation.Compilation;
import com.lionword.publicapi.compilations.service.PublicCompilationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class PublicCompilationsController {

    private final PublicCompilationsService publicCompilationsService;

    @GetMapping
    public List<Compilation> getCompilations(@RequestParam(name = "pinned") boolean pinned,
                                             @RequestParam(name = "from") int from,
                                             @RequestParam(name = "size") int size) {
        //stub
        return List.of();
    }


    @GetMapping("/{compId}")
    public Compilation getCompilationById(@PathVariable long compId) {
        //stub
        return new Compilation();
    }
}
