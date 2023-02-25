package com.lionword.entity.participation;

import java.time.LocalDateTime;

public class ParticipationRequestDto {
    private long id;
    private LocalDateTime created;
    private long event;
    private long requester;
    private RequestState state;
}
