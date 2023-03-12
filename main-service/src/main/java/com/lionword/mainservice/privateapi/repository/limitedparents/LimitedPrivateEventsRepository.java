package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateEventsRepository<T, S> extends Repository<T, S> {
    Page<T> findAllByInitiatorId(long initiatorId, Pageable pageable);

    Optional<T> findAllByIdAndInitiatorId(long eventId, long initiatorId);

    T save(T event);

    Optional<T> findById(long eventId);

    @Modifying
    @Query("UPDATE EventFullDto e SET e.confirmedRequests = e.confirmedRequests + :confirmedRequestsAmount" +
            " WHERE e.id = :eventId")
    void increaseConfirmedRequestsCounter(int confirmedRequestsAmount);

    @Modifying
    @Query("UPDATE EventFullDto e SET e.views = e.views + 1" +
            "WHERE e.id = :eventId")
    void addView(long eventId);

    @Modifying
    @Query("UPDATE EventFullDto e SET e.location = :locationId " +
            "WHERE e.id = :eventId")
    void addLocationId(long eventId, long locationId);
}
