package com.lionword.publicapi.events;

import com.lionword.entity.event.EventShortDto;
import com.lionword.entity.event.EventSort;
import com.lionword.publicapi.events.service.PublicEventsService;
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
    public List<EventShortDto> getEvents(@RequestParam(name = "text") String text,
                                         @RequestParam(name = "categories") List<Long> categories,
                                         @RequestParam(name = "paid") Boolean paid,
                                         @RequestParam(name = "rangeStart") String rangeStart,
                                         @RequestParam(name = "rangeEnd") String rangeEnd,
                                         @RequestParam(name = "onlyAvailable") Boolean onlyAvailable,
                                         @RequestParam(name = "sort") EventSort sort,
                                         @RequestParam(name = "from") int from,
                                         @RequestParam(name = "size") int size) {
        return publicEventsService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    /*Обратите внимание:

    -событие должно быть опубликовано
    -информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов
    -информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
    -в случае, если события с заданным id не найдено, возвращает статус код 404*/

    @GetMapping("/{id}")
    public EventShortDto getEventById(@PathVariable long id) {
        return publicEventsService.getEventById(id);
    }

}
