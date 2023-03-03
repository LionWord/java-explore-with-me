package com.lionword.privateapi.repository.limitedparents;

import com.lionword.entity.participation.ParticipationRequestDto;
import org.springframework.data.repository.CrudRepository;

public interface LimitedCrudParticipationRepository extends CrudRepository<ParticipationRequestDto, Long> {

    default ParticipationRequestDto save(ParticipationRequestDto request);
