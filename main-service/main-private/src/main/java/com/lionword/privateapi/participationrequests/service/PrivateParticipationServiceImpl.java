package com.lionword.privateapi.participationrequests.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventState;
import com.lionword.entity.participation.ParticipationRequestDto;
import com.lionword.privateapi.repository.ParticipationRepository;
import com.lionword.privateapi.repository.PrivateEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateParticipationServiceImpl {
    private final ParticipationRepository participationRepository;
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
         if (!event.isRequestModeration()) {
             participationRepository.
         }


    }
}
