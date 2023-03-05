package com.lionword.adminapi.events.service;

import com.lionword.adminapi.categories.repository.AdminCategoriesRepository;
import com.lionword.adminapi.events.repository.AdminEventsRepository;
import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventState;
import com.lionword.entity.event.StateActionAdmin;
import com.lionword.entity.event.UpdateEventAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl {

    private final AdminEventsRepository eventsRepository;
    private final AdminCategoriesRepository categoriesRepository;

    public List<EventFullDto> getEvents (List<Long> users,
                                         List<String> states,
                                         List<Long> categories,
                                         String rangeStart,
                                         String rangeEnd,
                                         int from,
                                         int size) {
        LocalDateTime start = LocalDateTime.parse(rangeStart);
        LocalDateTime end = LocalDateTime.parse(rangeEnd);
        Pageable pageable = PageRequest.of(from, size);
        return eventsRepository.searchEventByCriteria(users, states, categories, start, end, pageable);
    }

    public EventFullDto updateEvent(long eventId, UpdateEventAdminRequest update) {
        EventFullDto event = eventsRepository.findById(eventId).orElseThrow();
        if (!update.getAnnotation().isBlank()) {
            event.setAnnotation(update.getAnnotation());
        }
        if (update.getCategory() != null) {
            CategoryDto newCategory = categoriesRepository.findById(update.getCategory()).orElseThrow();
            event.setCategory(newCategory);
        }
        if (!update.getDescription().isBlank()) {
            event.setDescription(update.getDescription());
        }
        if (update.getEventDate() != null) {
            event.setEventDate(update.getEventDate());
        }
        if (update.getLocation() != null) {
            event.setLocation(update.getLocation());
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
            event.setState(EventState.PUBLISHED);
        }
        if (update.getStateAction().equals(StateActionAdmin.REJECT_EVENT)) {
            event.setState(EventState.CANCELED);
        }
        if (!update.getTitle().isBlank()) {
            event.setTitle(update.getTitle());
        }
        return eventsRepository.save(event);
    }


}
