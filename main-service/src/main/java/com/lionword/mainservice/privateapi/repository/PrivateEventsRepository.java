package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedPrivateEventsRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateEventsRepository extends LimitedPrivateEventsRepository<EventFullDto, Long> {

}
