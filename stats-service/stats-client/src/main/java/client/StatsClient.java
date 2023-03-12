package client;

import dto.EndpointHit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter

public class StatsClient extends BasicClient {

    public StatsClient(@Value("${stats-service.url}") String url, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> saveHit(HttpServletRequest request) {
        EndpointHit hit = new EndpointHit();
        hit.setIp(request.getRemoteAddr());
        hit.setUri(request.getRequestURI());
        hit.setApp(request.getServerName());
        hit.setTimestamp(LocalDateTime.now(Clock.systemUTC()));
        return post("/hit", hit);
    }

    public ResponseEntity<Object> getStats(String start, String end, String[] uris, boolean isUnique) {
        Map<String, Object> params = new HashMap<>(Map.of("start", start, "end", end));
        if (uris != null) {
            params.put("uris", uris);
        }
        if (isUnique) {
            params.put("unique", true);
        }

        return get("/stats", params);
    }


}
