package com.lionword.entity.compilation;

import com.lionword.entity.event.EventShortDto;
import lombok.NonNull;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "compilations")
public class CompilationDto {
    @Id
    @ReadOnlyProperty
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Transient
    private List<EventShortDto> events = List.of();
    @Column(name = "pinned")
    @NonNull
    private boolean pinned;
    @Column(name = "title")
    @NonNull
    private String title;
}
