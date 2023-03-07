package com.lionword.mainservice.adminapi.events.repository;

import com.lionword.mainservice.entity.event.EventFullDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdminEventsRepository extends JpaRepository<EventFullDto, Long> {

    /*@Query("SELECT e FROM EventFullDto e " +
            "JOIN FETCH CategoryDto cd " +
            "JOIN FETCH UserDto ud " +
            "JOIN FETCH Location l " +
            "JOIN FETCH Compilation c " +
            "JOIN FETCH e.compilationsIncludingThisEvent cite " +
            "WHERE " +
            "(:users IS EMPTY OR e.initiator.id IN :users) " +
            "AND (:states IS EMPTY OR e.state IN :states) " +
            "AND (:categories IS EMPTY OR e.category.id IN :categories) " +
            "AND (:rangeStart IS NULL OR e.eventDate >= :rangeStart) " +
            "AND (:rangeEnd IS NULL OR e.eventDate <= :rangeEnd) " +
            "ORDER BY e.eventDate DESC")*/
    @Query("select e from EventFullDto e ")
    List<EventFullDto> searchEventByCriteria(@Param("users") List<Long> users,
                                             @Param("states") List<String> states,
                                             @Param("categories") List<Long> categories,
                                             @Param("rangeStart") LocalDateTime rangeStart,
                                             @Param("rangeEnd") LocalDateTime rangeEnd,
                                             Pageable pageable);
}
