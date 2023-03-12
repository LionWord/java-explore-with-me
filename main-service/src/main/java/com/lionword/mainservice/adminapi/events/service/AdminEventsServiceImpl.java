package com.lionword.mainservice.adminapi.events.service;

import com.lionword.mainservice.adminapi.categories.repository.AdminCategoriesRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminEventsRepository;
import com.lionword.mainservice.adminapi.events.repository.AdminLocationRepository;
import com.lionword.mainservice.apierror.ExceptionTemplates;
import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import com.lionword.mainservice.entity.event.StateActionAdmin;
import com.lionword.mainservice.entity.event.UpdateEventAdminRequest;
import com.lionword.mainservice.entity.location.Location;
import com.lionword.mainservice.entity.util.InputValidator;
import com.lionword.mainservice.entity.util.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final AdminEventsRepository eventsRepo;
    private final AdminCategoriesRepository categoriesRepo;
    private final AdminLocationRepository locationRepo;

    @Override
    public List<EventFullDto> getEvents(List<Long> users,
                                        List<EventState> states,
                                        List<Long> categories,
                                        String rangeStart,
                                        String rangeEnd,
                                        int from,
                                        int size) {

        InputValidator.checkDateInput(rangeStart, rangeEnd);
        LocalDateTime start = LocalDateTime.parse(rangeStart, TimeFormatter.DEFAULT);
        LocalDateTime end = LocalDateTime.parse(rangeEnd, TimeFormatter.DEFAULT);
        Pageable pageable = PageRequest.of(from, size);
        return eventsRepo.searchEventByCriteria(users, states, categories, start, end, pageable).getContent();
    }

    @Override
    public EventFullDto updateEvent(long eventId, UpdateEventAdminRequest update) {
        EventFullDto event = eventsRepo.findById(eventId).orElseThrow();
        if (update.getAnnotation() != null && !update.getAnnotation().isBlank()) {
            event.setAnnotation(update.getAnnotation());
        }
        if (update.getCategory() != null) {
            CategoryDto newCategory = categoriesRepo.findById(update.getCategory()).orElseThrow();
            event.setCategory(newCategory);
        }
        if (update.getDescription() != null && !update.getDescription().isBlank()) {
            event.setDescription(update.getDescription());
        }
        if (update.getEventDate() != null) {
            if (event.getState().equals(EventState.PUBLISHED)) {
            if (update.getEventDate().minusHours(1).isBefore(event.getPublishedOn())) {
                ExceptionTemplates.eventTooEarlyAfterPublication();
            }
        }
                if (update.getEventDate().isBefore(event.getCreatedOn())) {
                    ExceptionTemplates.eventEarlierThanCreation();
                    event.setEventDate(update.getEventDate());
                }
                if (update.getEventDate().isBefore(LocalDateTime.now())) {
                    ExceptionTemplates.eventInPast();
                }

        }
                if (update.getLocation() != null) {
                    Location location = update.getLocation();
                    locationRepo.updateEventLocation(location.getLat(), location.getLon(), eventId);
                    event.setLocation(locationRepo.findByEventId(eventId));
                } else {
                    event.setLocation(locationRepo.findByEventId(eventId));
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
                if (update.getStateAction() != null) {
                    if (update.getStateAction().equals(StateActionAdmin.PUBLISH_EVENT)) {
                        if (event.getState().equals(EventState.PUBLISHED)) {
                            ExceptionTemplates.publishingAlreadyPublished();
                        }
                        if (event.getState().equals(EventState.CANCELED)) {
                            ExceptionTemplates.publishingCancelledEvent();
                        }
                        event.setPublishedOn(LocalDateTime.now());
                        event.setState(EventState.PUBLISHED);
                    }
                    if (update.getStateAction().equals(StateActionAdmin.REJECT_EVENT)) {
                        if (event.getState().equals(EventState.PUBLISHED)) {
                            ExceptionTemplates.cancellingPublishedEvent();
                        }
                        event.setState(EventState.CANCELED);
                    }
                }
                if (update.getTitle() != null && !update.getTitle().isBlank()) {
                    event.setTitle(update.getTitle());
                }
                return eventsRepo.save(event);
            }


    }
