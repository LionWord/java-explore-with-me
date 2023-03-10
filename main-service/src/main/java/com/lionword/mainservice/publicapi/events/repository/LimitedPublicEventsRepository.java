package com.lionword.mainservice.publicapi.events.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface LimitedPublicEventsRepository<T, S> extends Repository<T, S> {

    Optional<T> findById(long eventId);

    @Modifying
    @Query("UPDATE EventFullDto e SET e.views = e.views + 1" +
            "WHERE e.id = :eventId")
    void addView(long eventId);

    @Modifying
    @Query("UPDATE EventFullDto e SET e.views = e.views + 1" +
            "WHERE e.id IN :eventIds")
    void addViewToMultipleEvents(List<S> eventIds);

    @Query("SELECT e FROM EventFullDto e " +
            "JOIN FETCH CategoryDto cd ON e.category=cd " +
            "JOIN FETCH Location l ON e.location=l " +
            "JOIN FETCH UserDto ud ON e.initiator=ud " +
            "WHERE (:onlyAvailable = true AND e.confirmedRequests < e.participantLimit) OR (:onlyAvailable = false AND 1 = 1) " +
            "AND (LOWER(e.description) LIKE LOWER(:text) " +
            "OR LOWER(e.annotation) LIKE LOWER(:text)) " +
            "and e.category.id IN :categories " +
            "AND e.eventDate >= :rangeStart " +
            "AND e.eventDate <= :rangeEnd " +
            "AND e.paid = :paid " +
            "ORDER BY e.eventDate DESC")
    List<T> searchEventByCriteriaSortByDate(String text,
                                            List<S> categories,
                                            Boolean paid,
                                            LocalDateTime rangeStart,
                                            LocalDateTime rangeEnd,
                                            Boolean onlyAvailable,
                                            Pageable pageable);

    @Query("SELECT e FROM EventFullDto e " +
            "JOIN FETCH CategoryDto cd ON e.category=cd " +
            "JOIN FETCH Location l ON e.location=l " +
            "JOIN FETCH UserDto ud ON e.initiator=ud " +
            "WHERE (:onlyAvailable = true AND e.confirmedRequests < e.participantLimit) OR (:onlyAvailable = false AND 1 = 1) " +
            "AND (LOWER(e.description) LIKE LOWER(:text) " +
            "OR LOWER(e.annotation) LIKE LOWER(:text)) " +
            "and e.category.id IN :categories " +
            "AND e.eventDate >= :rangeStart " +
            "AND e.eventDate <= :rangeEnd " +
            "AND e.paid = :paid " +
            "ORDER BY e.views DESC ")
    List<T> searchEventByCriteriaSortByViews(String text,
                                             List<S> categories,
                                             Boolean paid,
                                             LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd,
                                             Boolean onlyAvailable,
                                             Pageable pageable);

}

