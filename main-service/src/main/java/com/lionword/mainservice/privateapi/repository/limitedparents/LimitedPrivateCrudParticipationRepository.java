package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LimitedPrivateCrudParticipationRepository extends CrudRepository<ParticipationRequestDto, Long> {
    @NonNull ParticipationRequestDto save(ParticipationRequestDto request);
}