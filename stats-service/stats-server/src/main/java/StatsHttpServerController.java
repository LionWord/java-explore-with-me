import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsHttpServerController {

    private final StatsServerService service;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRequestInfo(@RequestBody EndpointHit hit) {
        service.saveRequestInfo(hit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(@RequestParam(name = "start") String start,
                                    @RequestParam(name = "end") String end,
                                    @RequestParam(name = "uris", required = false) String[] uris,
                                    @RequestParam(name = "unique", required = false) boolean isUnique) {
        return service.getStats(start, end, uris, isUnique);
    }
}
