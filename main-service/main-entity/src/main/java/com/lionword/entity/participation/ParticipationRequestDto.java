package com.lionword.entity.participation;

import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "participation_requests")
public class ParticipationRequestDto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "event")
    private long event;
    @Column(name = "requester")
    private long requester;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestState status;
}
