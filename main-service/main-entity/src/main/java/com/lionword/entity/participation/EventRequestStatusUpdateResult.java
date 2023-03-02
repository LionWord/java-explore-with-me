package com.lionword.entity.participation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventRequestStatusUpdateResult {
    private List<ParticipationRequestDto> confirmedRequests = List.of();
    private List<ParticipationRequestDto> rejectedRequests = List.of();
}
