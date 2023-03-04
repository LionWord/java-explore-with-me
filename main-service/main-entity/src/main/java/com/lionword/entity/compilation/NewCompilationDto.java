package com.lionword.entity.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NewCompilationDto {
    private List<Long> events;
    private Boolean pinned;
    private String title;
}
