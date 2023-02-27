package com.lionword.privateapi.participationrequests.repository;

import com.lionword.entity.participation.ParticipationRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateParticipationRepository extends JpaRepository<ParticipationRequestDto, Long> {
}
