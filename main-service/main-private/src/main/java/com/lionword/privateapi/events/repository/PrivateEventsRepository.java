package com.lionword.privateapi.events.repository;

import com.lionword.entity.event.EventFullDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateEventsRepository extends JpaRepository<EventFullDto, Long> {
}
