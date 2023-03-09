package com.lionword.mainservice.adminapi.events.service;

import com.lionword.mainservice.adminapi.categories.repository.AdminCategoriesRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminLocationRepository;
import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.event.StateActionAdmin;
import com.lionword.mainservice.entity.event.UpdateEventAdminRequest;
import com.lionword.mainservice.entity.location.Location;
import com.lionword.mainservice.entity.util.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService{

    private final AdminEventsRepository eventsRepository;
    private final AdminCategoriesRepository categoriesRepository;
    private final AdminLocationRepository locationRepository;

    @Override
    public List<EventFullDto> getEvents(List<Long> users,
                                        List<EventState> states,
                                        List<Long> categories,
                                        String rangeStart,
                                        String rangeEnd,
                                        int from,
                                        int size) {
        LocalDateTime start = LocalDateTime.parse(rangeStart, TimeFormatter.DEFAULT);
        LocalDateTime end = LocalDateTime.parse(rangeEnd, TimeFormatter.DEFAULT);
        Pageable pageable = PageRequest.of(from, size);
        return eventsRepository.searchEventByCriteria(users, states, categories, start, end, pageable).getContent();
    }

    @Override
    public EventFullDto updateEvent(long eventId, UpdateEventAdminRequest update) {
        EventFullDto event = eventsRepository.findById(eventId).orElseThrow();
        if (update.getAnnotation() != null && !update.getAnnotation().isBlank()) {
            event.setAnnotation(update.getAnnotation());
        }
        if (update.getCategory() != null) {
            CategoryDto newCategory = categoriesRepository.findById(update.getCategory()).orElseThrow();
            event.setCategory(newCategory);
        }
        if (update.getDescription() != null && !update.getDescription().isBlank()) {
            event.setDescription(update.getDescription());
        }
        if (update.getEventDate() != null) {
            event.setEventDate(update.getEventDate());
        }
        if (update.getLocation() != null) {
            Location location = update.getLocation();
            locationRepository.updateEventLocation(location.getLat(), location.getLon(), eventId);
            event.setLocation(locationRepository.findByEventId(eventId));
        } else {
            event.setLocation(locationRepository.findByEventId(eventId));
        }

        if (update.getPaid() != null) {
            event.setPaid(update.getPaid());
        }
        if (update.getParticipantLimit() != null) {
            event.setParticipantLimit(update.getParticipantLimit());
        }
        if (update.getRequestModeration() != null) {
            event.setRequestModeration(update.getRequestModeration());
        }
        if (update.getStateAction().equals(StateActionAdmin.PUBLISH_EVENT)) {
            event.setPublishedOn(LocalDateTime.now());
            event.setState(EventState.PUBLISHED);
        }
        if (update.getStateAction().equals(StateActionAdmin.REJECT_EVENT)) {
            event.setState(EventState.CANCELED);
        }
        if (update.getTitle() != null && !update.getTitle().isBlank()) {
            event.setTitle(update.getTitle());
        }
        return eventsRepository.save(event);
    }


}
