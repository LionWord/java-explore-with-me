package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LimitedCrudParticipationRepository extends CrudRepository<ParticipationRequestDto, Long> {
    ParticipationRequestDto save(ParticipationRequestDto request);
}