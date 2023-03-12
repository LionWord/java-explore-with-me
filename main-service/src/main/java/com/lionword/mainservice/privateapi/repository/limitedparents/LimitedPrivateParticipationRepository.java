package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.participation.RequestState;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateParticipationRepository<T, S> extends Repository<T, S> {
    List<T> findAllByEventAndRequester(long eventId, long userId);

    List<T> findAllByEvent(long eventId);

    Optional<T> findById(long requestId);

    List<T> findAllByRequester(long userId);

    Optional<T> findByIdAndRequester(long requestId, long userId);

    @Modifying
    @Transactional
    @Query("UPDATE ParticipationRequestDto SET status = :status " +
            "WHERE id IN :ids")
    void changeParticipationRequestsStatus(List<S> ids, RequestState status);

    @Modifying
    @Transactional
    @Query("UPDATE ParticipationRequestDto SET status = :status " +
            "WHERE id = :id")
    void changeSingleParticipationRequestStatus(S id, RequestState status);

}
