package com.lionword.mainservice.entity.participation;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EventRequestStatusUpdateResult {
    private ArrayList<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
    private ArrayList<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
}
