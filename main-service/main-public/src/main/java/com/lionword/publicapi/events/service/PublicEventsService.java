package com.lionword.publicapi.events.service;

import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.EventSort;

import java.util.List;

public interface PublicEventsService {
    List<EventShortDto> getEvents(String text,
                                  List<Long> categories,
                                  Boolean paid,
                                  String rangeStart,
                                  String rangeEnd,
                                  Boolean onlyAvailable,
                                  EventSort sort,
                                  int from,
                                  int size);
    EventShortDto getEventById(long id);
}
