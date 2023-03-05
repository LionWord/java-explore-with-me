package com.lionword.adminapi.events.repository;

import com.lionword.entity.event.EventFullDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminEventsRepository extends JpaRepository<EventFullDto, Long> {

    @Query("SELECT e FROM EventFullDto e WHERE " +
            "(:users IS EMPTY OR e.initiator.id IN :users) " +
            "AND (:states IS EMPTY OR e.state IN :states) " +
            "AND (:categories IS EMPTY OR e.category.id IN :categories) " +
            "AND (:rangeStart IS NULL OR e.eventDate >= :rangeStart) " +
            "AND (:rangeEnd IS NULL OR e.eventDate <= :rangeEnd) " +
            "ORDER BY e.eventDate DESC")
    List<EventFullDto> searchEventByCriteria(List<Long> users,
                                             List<String> states,
                                             List<Long> categories,
                                             LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd,
                                             Pageable pageable);
}
