package server;

import dto.EndpointHit;
import dto.ViewStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsServerRepo extends JpaRepository<EndpointHit, Long> {

    @Query("select e.app as app, e.uri as uri, count(distinct e.ip) as hits from EndpointHit e" +
            " where e.timestamp between ?1 and ?2" +
            " group by app, uri" +
            " order by hits desc ")
    List<ViewStats> getAllDistinctEntriesInTimeRange(LocalDateTime start, LocalDateTime end);


    @Query("select e.app as app, e.uri as uri, count(e.ip) as hits from EndpointHit e" +
            " where e.timestamp between ?1 and ?2" +
            " group by app, uri" +
            " order by hits desc ")
    List<ViewStats> getAllNonDistinctEntriesInTimeRange(LocalDateTime start, LocalDateTime end);
}
