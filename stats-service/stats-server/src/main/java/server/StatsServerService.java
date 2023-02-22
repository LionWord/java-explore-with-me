package server;

import dto.EndpointHit;
import dto.ViewStats;

import java.util.List;

public interface StatsServerService {

    List<ViewStats> getStats(String start, String end, String[] uris, boolean isUnique);

    void saveRequestInfo(EndpointHit hit);
}
