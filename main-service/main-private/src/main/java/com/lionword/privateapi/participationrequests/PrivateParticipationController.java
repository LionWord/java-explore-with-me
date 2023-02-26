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
        //stub
        return List.of();
    }

    /*Обратите внимание:
    нельзя добавить повторный запрос (Ожидается код ошибки 409)
    инициатор события не может добавить запрос на участие в своём событии (Ожидается код ошибки 409)
    нельзя участвовать в неопубликованном событии (Ожидается код ошибки 409)
    если у события достигнут лимит запросов на участие - необходимо вернуть ошибку (Ожидается код ошибки 409)
    если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addParticipationRequest(@PathVariable long userId,
                                                                  @RequestParam(name = "eventId") long eventId) {
        //stub
        return null;
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelParticipationRequest(@PathVariable long userId, @PathVariable long requestId) {
        //stub
        return null;
    }

}
