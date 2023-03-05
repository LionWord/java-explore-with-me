package com.lionword.publicapi.events.repository;

import com.lionword.entity.event.EventFullDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicEventsRepository extends LimitedPublicEventsRepository<EventFullDto, Long> {
}
