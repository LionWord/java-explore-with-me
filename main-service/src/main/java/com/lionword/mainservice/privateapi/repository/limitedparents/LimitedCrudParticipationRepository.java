package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import org.springframework.data.repository.CrudRepository;

public interface LimitedCrudParticipationRepository extends CrudRepository<ParticipationRequestDto, Long> {
    ParticipationRequestDto save(ParticipationRequestDto request);
}