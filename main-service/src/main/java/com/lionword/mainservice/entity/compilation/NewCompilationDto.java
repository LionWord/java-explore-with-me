package com.lionword.mainservice.entity.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewCompilationDto {
    private List<Long> events;
    private Boolean pinned;
    private String title;
}
