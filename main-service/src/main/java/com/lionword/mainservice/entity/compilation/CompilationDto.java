package com.lionword.mainservice.entity.compilation;

import com.lionword.mainservice.entity.event.EventShortDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompilationDto {
    private long id;
    private List<EventShortDto> events;
    private Boolean pinned;
    private String title;
}
