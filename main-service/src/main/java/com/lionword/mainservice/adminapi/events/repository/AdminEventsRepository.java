package com.lionword.mainservice.adminapi.events.repository;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.event.EventFullDto;
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
            "JOIN FETCH CategoryDto cd on e.category=cd " +
            "JOIN FETCH Location l on e.location=l " +
            "JOIN FETCH UserDto ud on e.initiator=ud")
    Page<EventFullDto> searchEventByCriteria(@Param("users") List<Long> users,
                                             @Param("states") List<String> states,
                                             @Param("categories") List<Long> categories,
                                             @Param("rangeStart") LocalDateTime rangeStart,
                                             @Param("rangeEnd") LocalDateTime rangeEnd,
                                             Pageable pageable);

}
