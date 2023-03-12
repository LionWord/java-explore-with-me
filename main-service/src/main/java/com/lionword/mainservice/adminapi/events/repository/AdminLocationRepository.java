package com.lionword.mainservice.adminapi.events.repository;

import com.lionword.mainservice.entity.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AdminLocationRepository extends JpaRepository<Location, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Location l " +
            "SET l.lat = :lat, l.lon = :lon " +
            "WHERE l.eventId = :eventId")
    void updateEventLocation(@Param("lat") float lat,
                             @Param("lon") float lon,
                             @Param("eventId") long eventId);

    Location findByEventId(long eventId);

}
