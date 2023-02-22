package server;

import dto.EndpointHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsServerRepo extends JpaRepository<EndpointHit, Long> {
    @Query("select e from EndpointHit e " +
            " where e.timestamp between ?1 and ?2 ")
    List<EndpointHit> getAllEntriesInTimeRange(LocalDateTime start, LocalDateTime end);

    @Query("select count(distinct e.ip) from EndpointHit e " +
            " where e.timestamp between ?1 and ?2 ")
    Long countUniqueIpValues(LocalDateTime start, LocalDateTime end);
}
