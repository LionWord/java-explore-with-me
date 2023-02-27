package com.lionword.adminapi.events.repository;

import com.lionword.entity.event.EventFullDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEventsRepository extends JpaRepository<EventFullDto, Long> {
}
