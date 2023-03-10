package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedPrivateParticipationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateParticipationRepository extends LimitedPrivateParticipationRepository<ParticipationRequestDto, Long> {
}
