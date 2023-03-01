package com.lionword.entity.event;

import com.lionword.entity.location.Location;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
public class UpdateEventUserRequest {
    //minmax
    private String annotation;
    private Long category;
    //minmax
    private String description;
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private StateActionUser stateAction;
    //minmax
    private String title;
}
