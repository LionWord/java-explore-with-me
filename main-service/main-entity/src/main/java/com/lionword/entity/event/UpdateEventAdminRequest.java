package com.lionword.entity.event;

import com.lionword.entity.location.Location;

import java.time.LocalDateTime;

public class UpdateEventAdminRequest {
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
    private StateActionAdmin stateAction;
    //minmax
    private String title;
}
