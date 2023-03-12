package com.lionword.mainservice.publicapi.events;

import com.lionword.mainservice.entity.event.EventFullDto;
import com.lionword.mainservice.entity.event.EventShortDto;
import com.lionword.mainservice.entity.event.EventSort;
import com.lionword.mainservice.publicapi.events.service.PublicEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventsController {

    private final PublicEventsService publicEventsService;

    /*Обратите внимание:
-это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
-текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
-если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени
-информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие
-информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
-в случае, если по заданным фильтрам не найдено ни одного события, возвращает пустой список*/


    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text", required = false) String text,
                                         @RequestParam(name = "categories", required = false) List<Long> categories,
                                         @RequestParam(name = "paid", required = false) Boolean paid,
                                         @RequestParam(name = "rangeStart", required = false, defaultValue = "1111-01-01 00:00:01") String rangeStart,
                                         @RequestParam(name = "rangeEnd", required = false, defaultValue = "9999-01-01 00:00:01") String rangeEnd,
                                         @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
                                         @RequestParam(name = "sort", required = false, defaultValue = "EVENT_DATE") EventSort sort,
                                         @RequestParam(name = "from", required = false, defaultValue = "0") int from,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return publicEventsService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    /*Обратите внимание:

    -событие должно быть опубликовано
    -информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов
    -информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
    -в случае, если события с заданным id не найдено, возвращает статус код 404*/

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable long id) {
        return publicEventsService.getEventById(id);
    }

}
