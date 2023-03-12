package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.participation.ParticipationRequestDto;
import com.lionword.mainservice.entity.participation.RequestState;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface LimitedPrivateParticipationRepository<ParticipationRequestDto, Long> extends Repository<ParticipationRequestDto, Long> {
    List<ParticipationRequestDto> findAllByEventAndRequester(long eventId, long userId);

    List<ParticipationRequestDto> findAllByEvent(long eventId);
    Optional<ParticipationRequestDto> findById(long requestId);

    List<ParticipationRequestDto> findAllByRequester(long userId);

    Optional<ParticipationRequestDto> findByIdAndRequester(long requestId, long userId);

    @Modifying
    @Transactional
    @Query("UPDATE ParticipationRequestDto SET status = :status " +
            "WHERE id IN :ids")
    void changeParticipationRequestsStatus(List<Long> ids, RequestState status);

    @Modifying
    @Transactional
    @Query("UPDATE ParticipationRequestDto SET status = :status " +
            "WHERE id = :id")
    void changeSingleParticipationRequestStatus(Long id, RequestState status);

}
