package com.lionword.mainservice.publicapi.events.service;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.EventSort;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.util.EventsMapper;
import com.lionword.mainservice.publicapi.events.repository.PublicEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicEventsServiceImpl implements PublicEventsService {

    private final PublicEventsRepository eventsRepository;

    public List<EventShortDto> getEvents(String text,
                                         List<Long> categories,
                                         Boolean paid,
                                         String rangeStart,
                                         String rangeEnd,
                                         Boolean onlyAvailable,
                                         EventSort sort,
                                         int from,
                                         int size) {

        LocalDateTime start = LocalDateTime.parse(rangeStart);
        LocalDateTime end = LocalDateTime.parse(rangeEnd);
        Pageable pageable = PageRequest.of(from, size);

        return eventsRepository.searchEventByCriteria(text, categories, paid, start, end, onlyAvailable, sort, pageable)
                .stream()
                .map(EventsMapper::mapToShort)
                .collect(Collectors.toList());
    }

    public EventShortDto getEventById(long id) {
        EventFullDto event = eventsRepository.findById(id).orElseThrow();
        if (!event.getState().equals(EventState.PUBLISHED)) {
            //stub
            throw new RuntimeException();
        }
        return EventsMapper.mapToShort(event);
    }

}
