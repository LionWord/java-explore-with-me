package com.lionword.privateapi.events.repository;

import com.lionword.entity.event.EventFullDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateEventsRepository extends JpaRepository<EventFullDto, Long> {

    List<EventFullDto> findAllByInitiatorId(long initiatorId);
    EventFullDto findByIdAndInitiatorId(long eventId, long initiatorId);
}
