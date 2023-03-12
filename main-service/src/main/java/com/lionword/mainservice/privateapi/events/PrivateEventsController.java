package com.lionword.mainservice.privateapi.events;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.UpdateEventUserRequest;
import com.lionword.mainservice.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.mainservice.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.privateapi.events.service.PrivateEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventsController {

    private final PrivateEventsService privateEventsService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "from", required = false, defaultValue = "0") int from,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                         @PathVariable long userId) {
        return privateEventsService.getEvents(from, size, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto addEvent(@PathVariable long userId, @RequestBody EventFullDto event) {
        return privateEventsService.addEvent(userId, event);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable long userId, @PathVariable long eventId) {
        return privateEventsService.getEventByInitiatorAndId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEvent(@PathVariable long userId, @PathVariable long eventId,
                                    @RequestBody UpdateEventUserRequest updateEvent) {
        return privateEventsService.updateEvent(userId, eventId, updateEvent);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getParticipationRequests(@PathVariable long userId, @PathVariable long eventId) {
        return privateEventsService.getParticipationRequests(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult changeRequestStatus(@PathVariable long userId, @PathVariable long eventId,
                                                              @RequestBody EventRequestStatusUpdateRequest updateRequestStatus) {
        return privateEventsService.changeRequestStatus(userId, eventId, updateRequestStatus);
    }

}
