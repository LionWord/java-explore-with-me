package com.lionword.mainservice.entity.util;

import com.lionword.mainservice.entity.compilation.Compilation;
import com.lionword.mainservice.entity.compilation.CompilationDto;
import com.lionword.mainservice.entity.event.EventShortDto;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationsMapper {

    public static CompilationDto mapToDto(Compilation compilation) {
        CompilationDto mappedCompilation = new CompilationDto();
        List<EventShortDto> events = compilation.getEvents().stream()
                .map(EventsMapper::mapToShort)
                .collect(Collectors.toList());
        mappedCompilation.setId(compilation.getId());
        mappedCompilation.setTitle(compilation.getTitle());
        mappedCompilation.setPinned(compilation.getPinned());
        mappedCompilation.setEvents(events);
        return mappedCompilation;
    }
}
