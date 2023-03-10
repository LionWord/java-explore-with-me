package com.lionword.mainservice.privateapi.events.service;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.UpdateEventUserRequest;
import com.lionword.mainservice.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.mainservice.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.mainservice.entity.participation.ParticipationRequestDto;

import java.util.List;

public interface PrivateEventsService {
    List<EventShortDto> getEvents(int from, int size, long userId);

    EventFullDto addEvent(long userId, EventFullDto event);

    EventFullDto getEventByInitiatorAndId(long userId, long eventId);

    EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent);

    List<ParticipationRequestDto> getParticipationRequests(long userId, long eventId);

    EventRequestStatusUpdateResult changeRequestStatus(long userId, long eventId, EventRequestStatusUpdateRequest updateRequestStatus);
}
