package com.lionword.mainservice.entity.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateCompilationRequest {
    List<Long> events;
    Boolean pinned;
    String title;
}
