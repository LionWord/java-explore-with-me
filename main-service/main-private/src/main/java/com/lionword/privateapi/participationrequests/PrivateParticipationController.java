package com.lionword.privateapi.participationrequests;

import com.lionword.entity.participation.ParticipationRequestDto;
import com.lionword.privateapi.participationrequests.service.PrivateParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class PrivateParticipationController {

    private final PrivateParticipationService privateParticipationService;

    @GetMapping
    public List<ParticipationRequestDto> getParticipationRequests(@PathVariable long userId) {
        return privateParticipationService.getParticipationRequests(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addParticipationRequest(@PathVariable long userId,
                                                                  @RequestParam(name = "eventId") long eventId) {
        return privateParticipationService.addParticipationRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelParticipationRequest(@PathVariable long userId, @PathVariable long requestId) {
        return cancelParticipationRequest(userId, requestId);
    }

}
