package com.lionword.privateapi.events.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.EventState;
import com.lionword.entity.event.UpdateEventUserRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.entity.participation.ParticipationRequestDto;
import com.lionword.privateapi.events.repository.CategoryRepository;
import com.lionword.privateapi.events.repository.ParticipationRepository;
import com.lionword.privateapi.events.repository.PrivateEventsRepository;
import com.lionword.privateapi.events.repository.UserRepository;
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
public class PrivateEventsServiceImpl {

    private final PrivateEventsRepository eventRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final ParticipationRepository participationRepo;

    public List<EventShortDto> getEvents(int from, int size, long userId) {
        Page<EventFullDto> events = eventRepo.findAllByInitiatorId(userId, PageRequest.of(from, size));
        return events.stream()
                .map(EventsMapper::mapToShort)
                .collect(Collectors.toList());
    }

    public EventFullDto addEvent(long userId, EventFullDto event) {
        event.setInitiator(userRepo.findById(userId));
        event.setCreatedOn(LocalDateTime.from(Instant.now()));
        event.setState(EventState.PENDING);
        return eventRepo.save(event);
    }

    public EventFullDto getEventById(long userId, long eventId) {
        return eventRepo.findByIdAndInitiatorId(eventId, userId);
    }

    public EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEvent) {
        EventFullDto event = eventRepo.findByIdAndInitiatorId(eventId, userId);
        return eventRepo.save(updateEvent(event, updateEvent));
    }

    public List<ParticipationRequestDto> getParticipationRequests(long userId, long eventId) {
        return participationRepo.findAllByEventAndRequester(eventId, userId);
    }

    public EventRequestStatusUpdateResult changeRequestStatus(long userId, long eventId, EventRequestStatusUpdateRequest updateRequestStatus) {
        /*Обратите внимание:

если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется
нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие (Ожидается код ошибки 409)
статус можно изменить только у заявок, находящихся в состоянии ожидания (Ожидается код ошибки 409)
если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить*/

        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();
        EventFullDto event = eventRepo.findById(eventId).orElseThrow();
        List<ParticipationRequestDto> requests = updateRequestStatus.getRequestIds().stream()
                .map(participationRepo::findById)
                .collect(Collectors.toList());


        if (event.getParticipantLimit() == 0 || !event.isRequestModeration()) {

            result.setConfirmedRequests();
        }

        if (event.getConfirmedRequests() == event.getParticipantLimit()) {
            //stub exception
            throw new RuntimeException();
        }
        List<ParticipationRequestDto> requests = updateRequestStatus.getRequestIds().stream()
                .map(participationRepo::findById)
                .collect(Collectors.toList());

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
