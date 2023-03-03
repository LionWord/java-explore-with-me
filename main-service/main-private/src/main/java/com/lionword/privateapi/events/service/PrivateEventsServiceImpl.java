package com.lionword.privateapi.events.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.EventState;
import com.lionword.entity.event.UpdateEventUserRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.entity.participation.ParticipationRequestDto;
import com.lionword.entity.participation.RequestState;
import com.lionword.privateapi.repository.CategoryRepository;
import com.lionword.privateapi.repository.ParticipationRepository;
import com.lionword.privateapi.repository.PrivateEventsRepository;
import com.lionword.privateapi.repository.UserRepository;
import com.lionword.entity.util.EventsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateEventsServiceImpl implements PrivateEventsService {

    private final PrivateEventsRepository eventRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final ParticipationRepository participationRepo;
    @Override
    public List<EventShortDto> getEvents(int from, int size, long userId) {
        Page<EventFullDto> events = eventRepo.findAllByInitiatorId(userId, PageRequest.of(from, size));
        return events.stream()
                .map(EventsMapper::mapToShort)
                .collect(Collectors.toList());
    }
    @Override
    public EventFullDto addEvent(long userId, EventFullDto event) {
        LocalDateTime createdOn = LocalDateTime.from(Instant.now());
        if (event.getEventDate().minusHours(2).isBefore(createdOn)) {
            //stub
            throw new RuntimeException();
        }
        event.setInitiator(userRepo.findById(userId));
        event.setCreatedOn(LocalDateTime.from(Instant.now()));
        event.setState(EventState.PENDING);
        return eventRepo.save(event);
    }
    @Override
    public EventFullDto getEventById(long userId, long eventId) {
        return eventRepo.findByIdAndInitiatorId(eventId, userId);
    }
    @Override
    public EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent) {
        EventFullDto event = eventRepo.findByIdAndInitiatorId(eventId, userId);
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
        //code generated by chatgpt
        /* requests.stream()
            .limit(availableSlots)
            .forEach(participationRequestDto -> {
                participationRequestDto.setStatus(RequestState.CONFIRMED);
                result.getConfirmedRequests().add(participationRequestDto);
                event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            });

    if (requests.size() > availableSlots) {
        requests.subList(availableSlots, requests.size())
                .forEach(participationRequestDto -> {
                    participationRequestDto.setStatus(RequestState.REJECTED);
                    result.getRejectedRequests().add(participationRequestDto);
                });
    }
*/

        eventRepo.save(event);
        return result;
    }

    //------------------Service methods--------------------
    private EventFullDto updateEvent(EventFullDto event, UpdateEventUserRequest newInfo) {
        if (newInfo.getAnnotation() != null) {
            event.setAnnotation(newInfo.getAnnotation());
        }
        if (newInfo.getCategory() != null) {
            event.setCategory(categoryRepo.findById(newInfo.getCategory()));
        }
        if (newInfo.getDescription() != null) {
            event.setDescription(newInfo.getDescription());
        }
        if (newInfo.getEventDate() != null) {
            event.setEventDate(newInfo.getEventDate());
        }
        if  (newInfo.getLocation() != null) {
            event.setLocation(newInfo.getLocation());
        }
        if (newInfo.getPaid() != null) {
            event.setPaid(newInfo.getPaid());
        }
        if (newInfo.getParticipantLimit() != null) {
            event.setParticipantLimit(newInfo.getParticipantLimit());
        }
        if (newInfo.getRequestModeration() != null) {
            event.setRequestModeration(newInfo.getRequestModeration());
        }
        if (newInfo.getStateAction() != null) {
            switch(newInfo.getStateAction()) {
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
