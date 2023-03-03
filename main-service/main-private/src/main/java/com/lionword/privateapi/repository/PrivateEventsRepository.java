package com.lionword.privateapi.repository;

import com.lionword.entity.event.EventFullDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateEventsRepository extends JpaRepository<EventFullDto, Long> {

    Page<EventFullDto> findAllByInitiatorId(long initiatorId, Pageable pageable);
    EventFullDto findByIdAndInitiatorId(long eventId, long initiatorId);

}
