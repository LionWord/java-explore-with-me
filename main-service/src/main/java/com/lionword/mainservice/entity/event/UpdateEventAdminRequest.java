package com.lionword.mainservice.entity.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lionword.mainservice.entity.location.Location;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class UpdateEventAdminRequest {
    //minmax
    private String annotation;
    private Long category;
    //minmax
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private StateActionAdmin stateAction;
    //minmax
    private String title;
}
