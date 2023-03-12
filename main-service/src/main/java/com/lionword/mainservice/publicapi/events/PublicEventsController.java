package com.lionword.mainservice.publicapi.events;

import client.StatsClient;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.EventSort;
import com.lionword.mainservice.publicapi.events.service.PublicEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventsController {

    private final PublicEventsService publicEventsService;
    private final StatsClient client;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text", required = false) String text,
                                         @RequestParam(name = "categories", required = false) List<Long> categories,
                                         @RequestParam(name = "paid", required = false) Boolean paid,
                                         @RequestParam(name = "rangeStart", required = false, defaultValue = "1111-01-01 00:00:01") String rangeStart,
                                         @RequestParam(name = "rangeEnd", required = false, defaultValue = "9999-01-01 00:00:01") String rangeEnd,
                                         @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
                                         @RequestParam(name = "sort", required = false, defaultValue = "EVENT_DATE") EventSort sort,
                                         @RequestParam(name = "from", required = false, defaultValue = "0") int from,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                         HttpServletRequest request) {
        client.saveHit(request);
        return publicEventsService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable long id, HttpServletRequest request) {
        client.saveHit(request);
        return publicEventsService.getEventById(id);
    }

}
