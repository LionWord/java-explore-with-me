package com.lionword.mainservice.publicapi.events.repository;

import com.lionword.mainservice.entity.event.EventSort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface LimitedPublicEventsRepository<EventFullDto, Long> extends Repository<EventFullDto, Long> {

    Optional<EventFullDto> findById(long eventId);

    /*@Query("SELECT e FROM EventFullDto e " +
            "JOIN FETCH CategoryDto cd ON e.category=cd " +
            "JOIN FETCH Location l ON e.location=l " +
            "JOIN FETCH UserDto ud ON e.initiator=ud " +
            "WHERE CASE WHEN :onlyAvailable = true THEN (e.confirmedRequests < e.participantLimit) ELSE (:onlyAvailable = false) END " +
            "AND (LOWER(e.description) LIKE LOWER(:text) " +
            "OR LOWER(e.annotation) LIKE LOWER(:text)) " +
            "and e.category.id IN :categories " +
            "AND e.eventDate >= :rangeStart " +
            "AND e.eventDate <= :rangeEnd " +
            "AND e.paid = :paid " +
            "ORDER BY CASE " +
            "WHEN :sort LIKE 'EVENT_DATE' THEN e.eventDate " +
            "WHEN :sort LIKE 'VIEWS' THEN e.views " +
            "ELSE e.id END DESC ")*/
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
            "ORDER BY CASE " +
            "WHEN :sort LIKE 'EVENT_DATE' THEN e.eventDate " +
            "WHEN :sort LIKE 'VIEWS' THEN e.views " +
            "ELSE e.id END DESC ")
    List<EventFullDto> searchEventByCriteria(String text,
                                             List<Long> categories,
                                             Boolean paid,
                                             LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd,
                                             Boolean onlyAvailable,
                                             String sort,
                                             Pageable pageable);
}
