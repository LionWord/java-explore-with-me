package com.lionword.mainservice.privateapi.participationrequests.service;

import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.entity.participation.RequestState;
import com.lionword.mainservice.privateapi.repository.CrudParticipationRepository;
import com.lionword.mainservice.privateapi.repository.PrivateParticipationRepository;
import com.lionword.mainservice.privateapi.repository.PrivateEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateParticipationServiceImpl implements PrivateParticipationService{
    private final PrivateParticipationRepository participationRepository;
    private final CrudParticipationRepository crudParticipationRepository;
    private final PrivateEventsRepository eventsRepository;

    @Override
    public List<ParticipationRequestDto> getParticipationRequests(long userId) {
        return participationRepository.findAllByRequester(userId);
    }
    @Override
    public ParticipationRequestDto addParticipationRequest(long userId, long eventId) {
        EventFullDto event = eventsRepository.findById(eventId).orElseThrow();
        if (!participationRepository.findAllByEventAndRequester(eventId, userId).isEmpty()) {
            ExceptionTemplates.repeatedRequest();
        }
        if (event.getInitiator().getId() == userId) {
            ExceptionTemplates.requestToParticipateInOwnEvent();
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            ExceptionTemplates.notPublishedEventParticipation();
        }

        if (event.getParticipantLimit() == event.getConfirmedRequests()) {
            ExceptionTemplates.participationLimitExceeded();
        }

        ParticipationRequestDto newRequest = new ParticipationRequestDto();
        newRequest.setCreated(LocalDateTime.now());
        newRequest.setRequester(userId);
        newRequest.setEvent(eventId);
        if (!event.isRequestModeration()) {
            newRequest.setStatus(RequestState.CONFIRMED);
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            eventsRepository.save(event);
        }
        return crudParticipationRepository.save(newRequest);
    }
    @Override
    public ParticipationRequestDto cancelParticipationRequest(long userId, long requestId) {
        ParticipationRequestDto request = participationRepository.findByIdAndRequester(requestId, userId)
                .orElseThrow();
        EventFullDto event = eventsRepository.findById(request.getEvent()).orElseThrow();
        if (request.getStatus().equals(RequestState.CONFIRMED)) {
            event.setConfirmedRequests(event.getConfirmedRequests() - 1);
            eventsRepository.save(event);
        }
        request.setStatus(RequestState.CANCELED);
        return crudParticipationRepository.save(request);
    }

}
