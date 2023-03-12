package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@NoRepositoryBean
public interface LimitedPrivateLocationRepository<T, S> extends Repository<T, S> {
    T save(T location);

    @Modifying
    @Query("UPDATE Location l " +
            "SET l.lat = :lat, l.lon = :lon " +
            "WHERE l.eventId = :eventId")
    @Transactional
    void updateEventLocation(@Param("lat") float lat,
                             @Param("lon") float lon1,
                             @Param("eventId") long eventId);

    T findByEventId(long eventId);
}
