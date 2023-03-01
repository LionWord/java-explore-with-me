package com.lionword.privateapi.events.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.EventState;
import com.lionword.entity.event.UpdateEventUserRequest;
import com.lionword.privateapi.events.repository.CategoryRepository;
import com.lionword.privateapi.events.repository.PrivateEventsRepository;
import com.lionword.privateapi.events.repository.UserRepository;
import com.lionword.entity.util.EventsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateEventsServiceImpl {

    private final PrivateEventsRepository repo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public List<EventShortDto> getEvents(int from, int size, long userId) {
        List<EventFullDto> events = repo.findAllByInitiatorId(userId);
        return events.stream()
                .skip(from)
                .limit(size + 1)
                .map(EventsMapper::mapToShort)
                .collect(Collectors.toList());
    }

    public EventFullDto addEvent(long userId, EventFullDto event) {
        event.setInitiator(userRepo.findById(userId));
        event.setCreatedOn(LocalDateTime.from(Instant.now()));
        event.setState(EventState.PENDING);
        return repo.save(event);
    }

    public EventFullDto getEventById(long userId, long eventId) {
        return repo.findByIdAndInitiatorId(eventId, userId);
    }

    public EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent) {
        EventFullDto event = repo.findByIdAndInitiatorId(eventId, userId);
        return repo.save(updateEvent(event, updateEvent));
    }


    //------------------Service methods--------------------
    private EventFullDto updateEvent(EventFullDto event, UpdateEventUserRequest newInfo) {
        EventFullDto updatedEvent = event;
        if (newInfo.getAnnotation() != null) {
            updatedEvent.setAnnotation(newInfo.getAnnotation());
        }
        if (newInfo.getCategory() != null) {
            updatedEvent.setCategory(categoryRepo.findById(newInfo.getCategory()));
        }
        if (newInfo.getDescription() != null) {
            updatedEvent.setDescription(newInfo.getDescription());
        }
        if (newInfo.getEventDate() != null) {
            updatedEvent.setEventDate(newInfo.getEventDate());
        }
        if  (newInfo.getLocation() != null) {
            updatedEvent.setLocation(newInfo.getLocation());
        }
        if (newInfo.getPaid() != null) {
            updatedEvent.setPaid(newInfo.getPaid());
        }
        if (newInfo.getParticipantLimit() != null) {
            updatedEvent.setParticipantLimit(newInfo.getParticipantLimit());
        }
        if (newInfo.getRequestModeration() != null) {
            updatedEvent.setRequestModeration(newInfo.getRequestModeration());
        }
        if (newInfo.getStateAction() != null) {
            switch(newInfo.getStateAction()) {
                case CANCEL_REVIEW:
                    updatedEvent.setState(EventState.CANCELED);
                    break;
                case SEND_TO_REVIEW:
                    updatedEvent.setState(EventState.PENDING);
            }
        }
        if (newInfo.getTitle() != null) {
            updatedEvent.setTitle(newInfo.getTitle());
        }
        return updatedEvent;
    }

}
