package com.lionword.mainservice.adminapi.events.service;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.event.UpdateEventAdminRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminEventsService {
    List<EventFullDto> getEvents(List<Long> users,
                                 List<EventState> states,
                                 List<Long> categories,
                                 String rangeStart,
                                 String rangeEnd,
                                 int from,
                                 int size);

    EventFullDto updateEvent(long eventId, UpdateEventAdminRequest update);
}
