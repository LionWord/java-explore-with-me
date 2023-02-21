package server;

import dto.EndpointHit;
import dto.ViewStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServerServiceImpl implements StatsServerService{

    private final StatsServerRepo repo;

    @Override
    public List<ViewStats> getStats(String start, String end, String[] uris, boolean isUnique) {
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.from(formatter.parse(start));
        LocalDateTime endTime = LocalDateTime.from(formatter.parse(start));*/
        Timestamp startStamp = Timestamp.valueOf(start);
        Timestamp endStamp = Timestamp.valueOf(end);
        List<EndpointHit> hitsList = repo.getAllEntriesInTimeRange(startStamp, endStamp);
        return hitsList.stream()
                .map(endpointHit -> mapHitToViewStats(endpointHit, startStamp, endStamp))
                .collect(Collectors.toList());
    }

    @Override
    public void saveRequestInfo(EndpointHit hit) {
        repo.save(hit);
    }

    private ViewStats mapHitToViewStats(EndpointHit hit, Timestamp start, Timestamp end) {
        Long uniqueIpQuantity = repo.countUniqueIpValues(start, end);
        ViewStats viewStats = new ViewStats();
        viewStats.setApp(hit.getApp());
        viewStats.setUri(hit.getUri());
        viewStats.setHits(uniqueIpQuantity);
        return viewStats;
    }
}
