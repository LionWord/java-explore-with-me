package com.lionword.mainservice.adminapi.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lionword.mainservice.adminapi.events.service.AdminEventsService;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.event.UpdateEventAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
public class AdminEventsController {

    private final AdminEventsService adminEventsService;

    @GetMapping
    public List<EventFullDto> getEvents(@RequestParam(name = "users") List<Long> users,
                                        @RequestParam(name = "states") List<EventState> states,
                                        @RequestParam(name = "categories") List<Long> categories,
                                        @RequestParam(name = "rangeStart") String rangeStart,
                                        @RequestParam(name = "rangeEnd") String rangeEnd,
                                        @RequestParam(name = "from") int from,
                                        @RequestParam(name = "size") int size) {
        return adminEventsService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEvent(@PathVariable long eventId, @RequestBody UpdateEventAdminRequest update) {
        return adminEventsService.updateEvent(eventId, update);
    }

}
