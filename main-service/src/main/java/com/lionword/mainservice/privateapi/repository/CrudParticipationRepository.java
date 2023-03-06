package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedCrudParticipationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudParticipationRepository extends LimitedCrudParticipationRepository {
}
