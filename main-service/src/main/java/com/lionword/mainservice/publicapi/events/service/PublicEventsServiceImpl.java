package com.lionword.mainservice.publicapi.events.service;

import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.EventSort;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.util.EventsMapper;
import com.lionword.mainservice.entity.util.TimeFormatter;
import com.lionword.mainservice.publicapi.events.repository.PublicEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicEventsServiceImpl implements PublicEventsService {

    private final PublicEventsRepository eventRepo;

    @Override
    @Transactional
    public List<EventShortDto> getEvents(String text,
                                         List<Long> categories,
                                         Boolean paid,
                                         String rangeStart,
                                         String rangeEnd,
                                         Boolean onlyAvailable,
                                         EventSort sort,
                                         int from,
                                         int size) {

        LocalDateTime start = LocalDateTime.parse(rangeStart, TimeFormatter.DEFAULT);
        LocalDateTime end = LocalDateTime.parse(rangeEnd, TimeFormatter.DEFAULT);
        Pageable pageable = PageRequest.of(from, size);
        List<EventShortDto> result;
        List<Long> eventIds = new ArrayList<>();
        if (sort.equals(EventSort.EVENT_DATE)) {
            result = eventRepo.searchEventByCriteriaSortByDate(text, categories, paid, start, end, onlyAvailable, pageable)
                    .stream()
                    .map(EventsMapper::mapToShort)
                    .peek(eventShortDto -> eventIds.add(eventShortDto.getId()))
                    .peek(eventShortDto -> eventShortDto.setViews(eventShortDto.getViews() + 1))
                    .collect(Collectors.toList());
            if (eventIds.size() > 0) {
                eventRepo.addViewToMultipleEvents(eventIds);
            }
            return result;
        } else if (sort.equals(EventSort.VIEWS)) {
            result = eventRepo.searchEventByCriteriaSortByViews(text, categories, paid, start, end, onlyAvailable, pageable)
                    .stream()
                    .map(EventsMapper::mapToShort)
                    .peek(eventShortDto -> eventIds.add(eventShortDto.getId()))
                    .peek(eventShortDto -> eventShortDto.setViews(eventShortDto.getViews() + 1))
                    .collect(Collectors.toList());
            if (eventIds.size() > 0) {
                eventRepo.addViewToMultipleEvents(eventIds);
            }
            return result;
        }
        return List.of();

    }

    @Override
    @Transactional
    public EventFullDto getEventById(long id) {
        EventFullDto event = eventRepo.findById(id)
                .orElseThrow(ExceptionTemplates::eventNotFound);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            //stub
            throw new RuntimeException();
        }
        eventRepo.addView(id);
        event.setViews(event.getViews() + 1);
        return event;
    }

}
