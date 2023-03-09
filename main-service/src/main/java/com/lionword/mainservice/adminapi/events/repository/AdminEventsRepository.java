package com.lionword.mainservice.adminapi.events.repository;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdminEventsRepository extends JpaRepository<EventFullDto, Long> {

    @Query("SELECT e FROM EventFullDto e " +
            "JOIN FETCH CategoryDto cd ON e.category=cd " +
            "JOIN FETCH Location l ON e.location=l " +
            "JOIN FETCH UserDto ud ON e.initiator=ud " +
            "WHERE " +
            "e.initiator.id IN :users " +
            "AND e.state IN :states " +
            "AND e.category.id IN :categories " +
            "AND e.eventDate >= :rangeStart " +
            "AND e.eventDate <= :rangeEnd " +
            "ORDER BY e.eventDate DESC ")
    Page<EventFullDto> searchEventByCriteria(@Param("users") List<Long> users,
                                             @Param("states") List<EventState> states,
                                             @Param("categories") List<Long> categories,
                                             @Param("rangeStart") LocalDateTime rangeStart,
                                             @Param("rangeEnd") LocalDateTime rangeEnd,
                                             Pageable pageable);

}
