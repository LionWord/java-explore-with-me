package com.lionword.mainservice.privateapi.participationrequests.service;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;

import java.util.List;

public interface PrivateParticipationService {
    List<ParticipationRequestDto> getParticipationRequests(long userId);

    ParticipationRequestDto addParticipationRequest(long userId, long eventId);

    ParticipationRequestDto cancelParticipationRequest(long userId, long requestId);
}
