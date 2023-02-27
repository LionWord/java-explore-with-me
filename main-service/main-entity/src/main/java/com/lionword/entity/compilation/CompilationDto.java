package com.lionword.entity.compilation;

import com.lionword.entity.event.EventShortDto;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "compilations")
public class CompilationDto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Transient
    private List<EventShortDto> events = List.of();
    @Column(name = "pinned")
    private boolean pinned;
    @Column(name = "title")
    private String title;
}
