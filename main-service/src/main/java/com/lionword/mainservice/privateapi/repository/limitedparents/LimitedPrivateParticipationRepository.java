package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface LimitedPrivateParticipationRepository<ParticipationRequestDto, Long> extends Repository<ParticipationRequestDto, Long> {
    List<ParticipationRequestDto> findAllByEventAndRequester(long eventId, long userId);

    Optional<ParticipationRequestDto> findById(long requestId);

    List<ParticipationRequestDto> findAllByRequester(long userId);

    Optional<ParticipationRequestDto> findByIdAndRequester(long requestId, long userId);
}
