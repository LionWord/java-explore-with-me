package com.lionword.entity.compilation;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
