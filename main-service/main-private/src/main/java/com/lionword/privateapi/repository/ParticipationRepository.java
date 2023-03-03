package com.lionword.privateapi.repository;

import com.lionword.entity.participation.ParticipationRequestDto;
import com.lionword.privateapi.repository.limitedparents.LimitedParticipationRepository;

public interface ParticipationRepository extends LimitedParticipationRepository<ParticipationRequestDto, Long> {
}
