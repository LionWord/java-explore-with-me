package com.lionword.privateapi.repository.limitedparents;

import com.lionword.entity.participation.ParticipationRequestDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LimitedParticipationRepository<ParticipationRequestDto, Long> extends Repository<ParticipationRequestDto, Long> {
    List<ParticipationRequestDto> findAllByEventAndRequester(long eventId, long userId);
    ParticipationRequestDto findById(long requestId);
    List<ParticipationRequestDto> findAllByRequester(long userId);

}
