package com.lionword.mainservice.privateapi.events.service;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.event.UpdateEventUserRequest;
import com.lionword.mainservice.entity.location.Location;
import com.lionword.mainservice.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.mainservice.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.entity.participation.RequestState;
import com.lionword.mainservice.entity.util.EventsMapper;
import com.lionword.mainservice.privateapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateEventsServiceImpl implements PrivateEventsService {

    private final PrivateEventsRepository eventRepo;
    private final PrivateUserRepository userRepo;
    private final PrivateCategoryRepository categoryRepo;
    private final PrivateParticipationRepository participationRepo;
    private final LocationRepository locationRepo;

    @Override
    public List<EventShortDto> getEvents(int from, int size, long userId) {
        Page<EventFullDto> events = eventRepo.findAllByInitiatorId(userId, PageRequest.of(from, size));
        return events.stream()
                .map(EventsMapper::mapToShort)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto addEvent(long userId, EventFullDto event) {
        LocalDateTime createdOn = LocalDateTime.now();
        if (event.getEventDate().minusHours(2).isBefore(createdOn)) {
            //stub
            throw new RuntimeException();
        }
        event.setInitiator(userRepo.findById(userId).orElseThrow());
        event.setState(EventState.PENDING);
        event.setCategory(categoryRepo.findById(event.getCategory().getId()).orElseThrow());
        event = eventRepo.save(event);
        Location location = event.getLocation();
        locationRepo.save(event.getLocation());
        location.setEventId(event.getId());
        locationRepo.save(location);
        event.setLocation(locationRepo.findByEventId(event.getId()));

        event = eventRepo.save(event);
        return event;
    }

    @Override
    @Transactional
    public EventFullDto getEventByInitiatorAndId(long userId, long eventId) {
        EventFullDto event = eventRepo.findAllByIdAndInitiatorId(eventId, userId).orElseThrow();
        eventRepo.addView(eventId);
        event.setViews(event.getViews() + 1);
        return event;
    }

    @Override
    public EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent) {
        EventFullDto event = eventRepo.findAllByIdAndInitiatorId(eventId, userId).orElseThrow();
        if (event.getState().equals(EventState.PUBLISHED)) {
            //stub
            throw new RuntimeException();
        }
        if (updateEvent.getEventDate() != null) {
            if (updateEvent.getEventDate().minusHours(2).isBefore(LocalDateTime.from(Instant.now()))) {
                //stub
                throw new RuntimeException();
            }
        }
        return eventRepo.save(updateEvent(event, updateEvent));
    }

    @Override
    public List<ParticipationRequestDto> getParticipationRequests(long userId, long eventId) {
        return participationRepo.findAllByEventAndRequester(eventId, userId);
    }

    @Override
    public EventRequestStatusUpdateResult changeRequestStatus(long userId, long eventId, EventRequestStatusUpdateRequest updateRequestStatus) {
        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();
        EventFullDto event = eventRepo.findById(eventId).orElseThrow();
        List<ParticipationRequestDto> requests = updateRequestStatus.getRequestIds().stream()
                .map(participationRepo::findById)
                .collect(Collectors.toList());

        if (event.getParticipantLimit() == 0 || !event.isRequestModeration()) {
            result.setConfirmedRequests(requests);
            return result;
        }

        if (event.getConfirmedRequests() == event.getParticipantLimit()) {
            //stub exception
            throw new RuntimeException();
        }

        boolean haveNonPendingRequests = requests.stream()
                .anyMatch(participationRequestDto -> participationRequestDto.getStatus().equals(RequestState.CONFIRMED)
                        || participationRequestDto.getStatus().equals(RequestState.REJECTED));

        if (haveNonPendingRequests) {
            //stub exception
            throw new RuntimeException();
        }

        int availableSlots = event.getParticipantLimit() - event.getConfirmedRequests();
        //my code
        for (int i = 0; i <= availableSlots; i++) {
            if (requests.size() > 0 & i == availableSlots) {
                requests = requests.subList(i, requests.size());
                for (ParticipationRequestDto prd : requests) {
                    prd.setStatus(RequestState.REJECTED);
                    result.getRejectedRequests().add(prd);
                }
            }
            requests.get(i).setStatus(RequestState.CONFIRMED);
            result.getConfirmedRequests().add(requests.get(i));
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
        }
        eventRepo.save(event);
        return result;
    }

    //------------------Service methods--------------------
    private EventFullDto updateEvent(EventFullDto event, UpdateEventUserRequest newInfo) {
        if (newInfo.getAnnotation() != null) {
            event.setAnnotation(newInfo.getAnnotation());
        }
        if (newInfo.getCategory() != null) {
            event.setCategory(categoryRepo.findById(newInfo.getCategory()).orElseThrow());
        }
        if (newInfo.getDescription() != null) {
            event.setDescription(newInfo.getDescription());
        }
        if (newInfo.getEventDate() != null) {
            event.setEventDate(newInfo.getEventDate());
        }
        if (newInfo.getLocation() != null) {
            Location location = newInfo.getLocation();
            locationRepo.updateEventLocation(location.getLat(), location.getLon(), event.getId());
            event.setLocation(locationRepo.findByEventId(event.getId()));
        } else {
            event.setLocation(locationRepo.findByEventId(event.getId()));
        }
        if (newInfo.getParticipantLimit() != null) {
            event.setParticipantLimit(newInfo.getParticipantLimit());
        }
        if (newInfo.getRequestModeration() != null) {
            event.setRequestModeration(newInfo.getRequestModeration());
        }
        if (newInfo.getStateAction() != null) {
            switch (newInfo.getStateAction()) {
                case CANCEL_REVIEW:
                    event.setState(EventState.CANCELED);
                    break;
                case SEND_TO_REVIEW:
                    event.setState(EventState.PENDING);
            }
        }
        if (newInfo.getTitle() != null) {
            event.setTitle(newInfo.getTitle());
        }
        return event;
    }

}
