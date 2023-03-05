package com.lionword.adminapi.events.service;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventsService {
    List<EventFullDto> getEvents (List<Long> users,
                                  List<String> states,
                                  List<Long> categories,
                                  String rangeStart,
                                  String rangeEnd,
                                  int from,
                                  int size);
    EventFullDto updateEvent(long eventId, UpdateEventAdminRequest update);
}
