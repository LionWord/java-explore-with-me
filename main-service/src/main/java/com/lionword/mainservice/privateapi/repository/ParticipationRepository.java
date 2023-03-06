package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedParticipationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends LimitedParticipationRepository<ParticipationRequestDto, Long> {
}
