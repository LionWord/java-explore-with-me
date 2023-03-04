package com.lionword.entity.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UpdateCompilationRequest {
    List<Long> events;
    boolean pinned;
    String title;
}
