package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.location.Location;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@NoRepositoryBean
public interface LimitedPrivateLocationRepository<Location, Long> extends Repository<Location, Long> {
    Location save(Location location);
    @Modifying
    @Query("UPDATE Location l " +
            "SET l.lat = :lat, l.lon = :lon " +
            "WHERE l.eventId = :eventId")
    @Transactional
    void updateEventLocation(@Param("lat") float lat,
                                 @Param("lon") float lon1,
                                 @Param("eventId") long eventId);
    Location findByEventId(long eventId);
}
