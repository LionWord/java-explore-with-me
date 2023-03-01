package com.lionword.entity.util;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventShortDto;

public class EventsMapper {

    public static EventShortDto mapToShort(EventFullDto fullDto) {
        EventShortDto shortDto = new EventShortDto();
        shortDto.setId(fullDto.getId());
        shortDto.setAnnotation(fullDto.getAnnotation());
        shortDto.setCategory(fullDto.getCategory());
        shortDto.setConfirmedRequests(fullDto.getConfirmedRequests());
        shortDto.setEventDate(fullDto.getEventDate());
        shortDto.setInitiator(fullDto.getInitiator());
        shortDto.setPaid(fullDto.getPaid());
        shortDto.setTitle(fullDto.getTitle());
        shortDto.setViews(fullDto.getViews());
        return shortDto;
    }
}
