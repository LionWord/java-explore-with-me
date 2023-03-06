package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.event.EventFullDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateEventsRepository extends JpaRepository<EventFullDto, Long> {

    Page<EventFullDto> findAllByInitiatorId(long initiatorId, Pageable pageable);

    EventFullDto findByIdAndInitiatorId(long eventId, long initiatorId);

}
