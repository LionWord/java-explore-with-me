package com.lionword.privateapi.participationrequests.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventState;
import com.lionword.entity.participation.ParticipationRequestDto;
import com.lionword.entity.participation.RequestState;
import com.lionword.privateapi.repository.CrudParticipationRepository;
import com.lionword.privateapi.repository.ParticipationRepository;
import com.lionword.privateapi.repository.PrivateEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
