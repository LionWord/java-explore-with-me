package com.lionword.mainservice.privateapi.participationrequests.service;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.entity.participation.RequestState;
import com.lionword.mainservice.privateapi.repository.CrudParticipationRepository;
import com.lionword.mainservice.privateapi.repository.ParticipationRepository;
import com.lionword.mainservice.privateapi.repository.PrivateEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateParticipationServiceImpl {
    private final ParticipationRepository participationRepository;

    private final CrudParticipationRepository crudParticipationRepository;
    private final PrivateEventsRepository eventsRepository;

    public List<ParticipationRequestDto> getParticipationRequests(long userId) {
        return participationRepository.findAllByRequester(userId);
    }

    public ParticipationRequestDto addParticipationRequest(long userId, long eventId) {
        EventFullDto event = eventsRepository.findById(eventId).orElseThrow();
        if (!participationRepository.findAllByEventAndRequester(eventId, userId).isEmpty()) {
            //stub
            throw new RuntimeException();
        }
        if (event.getInitiator().getId() == userId) {
            //stub
            throw new RuntimeException();
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            //stub
            throw new RuntimeException();
        }

        if (event.getParticipantLimit() == event.getConfirmedRequests()) {
            //stub
            throw new RuntimeException();
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

    public ParticipationRequestDto cancelParticipationRequest(long userId, long requestId) {
        ParticipationRequestDto request = participationRepository.findByIdAndRequester(requestId, userId)
                .orElseThrow();
        EventFullDto event = eventsRepository.findById(request.getEvent()).orElseThrow();
        if (request.getStatus().equals(RequestState.CONFIRMED)) {
            event.setConfirmedRequests(event.getConfirmedRequests() - 1);
            eventsRepository.save(event);
        }
        request.setStatus(RequestState.CANCELLED);
        return crudParticipationRepository.save(request);
    }

}
