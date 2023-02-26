package com.lionword.entity.compilation;

import com.lionword.entity.event.EventShortDto;

import java.util.List;

public class CompilationDto {

    private long id;
    private List<EventShortDto> events;
    private boolean pinned;
    private String title;
}
