package com.lionword.publicapi.events.repository;

import com.lionword.entity.event.EventFullDto;
import com.lionword.entity.event.EventSort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LimitedPublicEventsRepository<EventFullDto, Long> extends Repository<EventFullDto, Long> {

    @Query("SELECT DISTINCT e " +
            "FROM EventFullDto e " +
            "LEFT JOIN FETCH e.initiator " +
            "LEFT JOIN FETCH e.category " +
            "WHERE (:text IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "AND (:categories IS NULL OR e.category.id IN :categories) " +
            "AND (:paid IS NULL OR e.paid = :paid) " +
            "AND (:rangeStart IS NULL OR e.eventDate >= :rangeStart) " +
            "AND (:rangeEnd IS NULL OR e.eventDate <= :rangeEnd) " +
            "AND (:onlyAvailable IS NULL OR e.confirmedRequests < e.participantLimit) " +
            "ORDER BY CASE " +
            "WHEN :sort = 'EVENT_DATE' THEN e.eventDate " +
            "WHEN :sort = 'VIEWS' THEN e.views " +
            "ELSE e.id END ASC")
    List<EventFullDto> searchEventByCriteria(String text,
                                             List<Long> categories,
                                             Boolean paid,
                                             LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd,
                                             Boolean onlyAvailable,
                                             EventSort sort,
                                             Pageable pageable);
    Optional<EventFullDto> findById(long eventId);
}
