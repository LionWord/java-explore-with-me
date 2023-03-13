package com.lionword.mainservice.privateapi.events.service;

import com.lionword.mainservice.apierror.ExceptionTemplates;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            ExceptionTemplates.eventTooEarlyAfterCreation();
        }
        if (event.getEventDate().isBefore(createdOn)) {
            ExceptionTemplates.eventInPast();
        }
        event.setInitiator(userRepo.findById(userId)
                .orElseThrow(ExceptionTemplates::userNotFound));
        event.setState(EventState.PENDING);
        event.setCategory(categoryRepo.findById(event.getCategory().getId())
                .orElseThrow(ExceptionTemplates::categoryNotFound));
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
        return eventRepo.findAllByIdAndInitiatorId(eventId, userId)
                .orElseThrow(ExceptionTemplates::eventNotFound);
    }

    @Override
    public EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent) {
        EventFullDto event = eventRepo.findAllByIdAndInitiatorId(eventId, userId)
                .orElseThrow(ExceptionTemplates::eventNotFound);
        if (event.getState().equals(EventState.PUBLISHED)) {
            ExceptionTemplates.alteringAlreadyPublishedEvent();
        }
        if (updateEvent.getEventDate() != null) {
            if (updateEvent.getEventDate().isBefore(LocalDateTime.now())) {
                ExceptionTemplates.eventInPast();
            }
            if (updateEvent.getEventDate().minusHours(2).isBefore(LocalDateTime.now())) {
                ExceptionTemplates.eventTooEarlyAfterCreation();
            }
        }

        return eventRepo.save(updateEvent(event, updateEvent));
    }

    @Override
    public List<ParticipationRequestDto> getParticipationRequests(long userId, long eventId) {
        EventFullDto event = eventRepo.findById(eventId)
                .orElseThrow(ExceptionTemplates::eventNotFound);
        if (event.getInitiator().getId() != userId) {
            ExceptionTemplates.notInitiatorRequest();
        }
        return participationRepo.findAllByEvent(eventId);
    }

    @Override
    public EventRequestStatusUpdateResult changeRequestStatus(long userId, long eventId, EventRequestStatusUpdateRequest updateRequestStatus) {
        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();
        int availableSlots;
        EventFullDto event = eventRepo.findById(eventId)
                .orElseThrow(ExceptionTemplates::eventNotFound);
        ArrayList<Long> requestIds = new ArrayList<>();
        ArrayList<ParticipationRequestDto> requests = updateRequestStatus.getRequestIds().stream()
                .map(participationRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(participationRequestDto -> requestIds.add(participationRequestDto.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Long> confirmedRequestsIds = new ArrayList<>();
        ArrayList<Long> rejectedRequestsIds = new ArrayList<>();

        boolean haveNonPendingRequests = requests.stream()
                .anyMatch(participationRequestDto -> participationRequestDto.getStatus().equals(RequestState.CONFIRMED)
                        || participationRequestDto.getStatus().equals(RequestState.REJECTED));

        /*if (haveNonPendingRequests) {
            ExceptionTemplates.changingNonPendingRequestStatus();
        }*/

        if (event.getParticipantLimit() == 0) {
            if (event.isRequestModeration()) {
                if (haveNonPendingRequests) {
                    ExceptionTemplates.changingNonPendingRequestStatus();
                }
                if (updateRequestStatus.getStatus().equals(RequestState.CONFIRMED)) {
                    result.setConfirmedRequests(requests);
                    event.setConfirmedRequests(event.getConfirmedRequests() + result.getConfirmedRequests().size());
                    eventRepo.save(event);
                } else {
                    result.setRejectedRequests(requests);
                }

                return result;
            } else {
                result.setConfirmedRequests(requests);
                event.setConfirmedRequests(event.getConfirmedRequests() + result.getConfirmedRequests().size());
                participationRepo.changeParticipationRequestsStatus(requestIds, updateRequestStatus.getStatus());
                eventRepo.save(event);
                return result;
            }

        }

        availableSlots = event.getParticipantLimit() - event.getConfirmedRequests();

        if (availableSlots == 0) {
            ExceptionTemplates.participationLimitExceeded();
        }

        if (event.getParticipantLimit() != 0) {
            if (!event.isRequestModeration()) {
                result.setConfirmedRequests(requests.stream()
                        .limit(availableSlots)
                        .peek(participationRequestDto -> confirmedRequestsIds.add(participationRequestDto.getId()))
                        .collect(Collectors.toCollection(ArrayList::new)));
                result.setRejectedRequests(requests.stream()
                        .skip(availableSlots)
                        .peek(participationRequestDto -> rejectedRequestsIds.add(participationRequestDto.getId()))
                        .collect(Collectors.toCollection(ArrayList::new)));
                event.setConfirmedRequests(event.getConfirmedRequests() + result.getConfirmedRequests().size());
                participationRepo.changeParticipationRequestsStatus(confirmedRequestsIds, RequestState.CONFIRMED);
                participationRepo.changeParticipationRequestsStatus(rejectedRequestsIds, RequestState.REJECTED);
                eventRepo.save(event);
                return result;
            }
        }

        for (int i = 0; i <= availableSlots; i++) {
            if (i == availableSlots & i < requests.size()) {
                requests = new ArrayList<>(requests.subList(i, requests.size()));
                for (ParticipationRequestDto prd : requests) {
                    prd.setStatus(RequestState.REJECTED);
                    result.getRejectedRequests().add(prd);
                }
            }
            if (i == requests.size()) {
                break;
            }
            requests.get(i).setStatus(updateRequestStatus.getStatus());
            if (updateRequestStatus.getStatus().equals(RequestState.CONFIRMED)) {
                participationRepo.changeSingleParticipationRequestStatus(requests.get(i).getId(), RequestState.CONFIRMED);
                result.getConfirmedRequests().add(requests.get(i));
                event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            } else {
                result.getRejectedRequests().add(requests.get(i));
                participationRepo.changeSingleParticipationRequestStatus(requests.get(i).getId(), RequestState.REJECTED);
            }
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
            event.setCategory(categoryRepo.findById(newInfo.getCategory())
                    .orElseThrow(ExceptionTemplates::categoryNotFound));
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
