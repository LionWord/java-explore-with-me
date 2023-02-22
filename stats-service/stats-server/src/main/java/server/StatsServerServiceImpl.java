package server;

import dto.EndpointHit;
import dto.ViewStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.exceptions.TimeParamsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServerServiceImpl implements StatsServerService {

    private final StatsServerRepo repo;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, boolean isUnique) {
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);
        if (startDate.isAfter(endDate)) {
            throw new TimeParamsException("Invalid query - check start and end params, probably start is after end");
        }
        List<ViewStats> hitsList;

        if (uris != null & isUnique) {
            hitsList = repo.getAllDistinctEntriesInTimeRange(startDate, endDate);
            return getStatsForUris(hitsList, uris);
        } else if (uris != null & !isUnique) {
            hitsList = repo.getAllNonDistinctEntriesInTimeRange(startDate, endDate);
            return getStatsForUris(hitsList, uris);
        } else if (isUnique) {
            return repo.getAllDistinctEntriesInTimeRange(startDate, endDate);
        } else {
            return repo.getAllNonDistinctEntriesInTimeRange(startDate, endDate);
        }
    }

    @Override
    public void saveRequestInfo(EndpointHit hit) {
        repo.save(hit);
    }

    private List<ViewStats> getStatsForUris(List<ViewStats> hitsList, List<String> uris) {
        return hitsList.stream()
                .filter(viewStats -> uris.contains(viewStats.getUri()))
                .collect(Collectors.toList());
    }

}
