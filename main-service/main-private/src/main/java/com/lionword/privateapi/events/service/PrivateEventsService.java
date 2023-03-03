package com.lionword.privateapi.events.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.UpdateEventUserRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.entity.participation.ParticipationRequestDto;

import java.util.List;

public interface PrivateEventsService {
    List<EventShortDto> getEvents(int from, int size, long userId);
    EventFullDto addEvent(long userId, EventFullDto event);
    EventFullDto getEventById(long userId, long eventId);
    EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent);
    List<ParticipationRequestDto> getParticipationRequests(long userId, long eventId);
    EventRequestStatusUpdateResult changeRequestStatus(long userId, long eventId, EventRequestStatusUpdateRequest updateRequestStatus);
}
