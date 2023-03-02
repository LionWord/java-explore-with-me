package com.lionword.privateapi.events;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.UpdateEventUserRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateRequest;
import com.lionword.entity.participation.EventRequestStatusUpdateResult;
import com.lionword.entity.participation.ParticipationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventsController {

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "from", required = false) int from,
                                         @RequestParam(name = "size", required = false) int size,
                                         @PathVariable long userId) {
        //stub
        return List.of();
    }

    /*Обратите внимание: дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента*/
    @PostMapping
    public EventFullDto addEvent(@PathVariable long userId, @RequestBody EventFullDto event) {
        //stub
        return null;
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable long userId, @PathVariable long eventId) {
        //stub
        return null;
    }

    /*Обратите внимание:

    изменить можно только отмененные события или события в состоянии ожидания модерации (Ожидается код ошибки 409)
    дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента (Ожидается код ошибки 409)*/
    @PostMapping("/{eventId}")
    public EventFullDto updateEvent(@PathVariable long userId, @PathVariable long eventId,
                                     @RequestBody UpdateEventUserRequest updateEvent) {
        //stub
        return null;
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getParticipationRequests(@PathVariable long userId, @PathVariable long eventId) {
        //stub
        return null;
    }

    /*Обратите внимание:

    если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется
    нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие (Ожидается код ошибки 409)
    статус можно изменить только у заявок, находящихся в состоянии ожидания (Ожидается код ошибки 409)
    если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить*/
    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult changeRequestStatus(@PathVariable long userId, @PathVariable long eventId,
                                                              @RequestBody EventRequestStatusUpdateRequest updateRequestStatus) {
        //stub
        return null;
    }

}
