package server;

import dto.EndpointHit;
import dto.ViewStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServerServiceImpl implements StatsServerService{

    private final StatsServerRepo repo;

    @Override
    public List<ViewStats> getStats(String start, String end, String[] uris, boolean isUnique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);
        List<EndpointHit> hitsList = repo.getAllEntriesInTimeRange(startDate, endDate);
        return hitsList.stream()
                .map(this::mapHitToViewStats)
                .collect(Collectors.toList());
    }

    @Override
    public void saveRequestInfo(EndpointHit hit) {
        repo.save(hit);
    }

    private ViewStats mapHitToViewStats(EndpointHit hit) {
        ViewStats viewStats = new ViewStats();
        viewStats.setApp(hit.getApp());
        viewStats.setUri(hit.getUri());
        viewStats.setHits(8L);
        return viewStats;
    }

}
