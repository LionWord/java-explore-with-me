package com.lionword.mainservice.publicapi.events.repository;

import com.lionword.mainservice.entity.event.EventFullDto;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicEventsRepository extends LimitedPublicEventsRepository<EventFullDto, Long> {
}
