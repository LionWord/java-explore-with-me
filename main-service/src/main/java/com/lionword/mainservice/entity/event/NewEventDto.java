package com.lionword.mainservice.entity.event;

import com.lionword.mainservice.entity.location.Location;

import java.time.LocalDateTime;

public class NewEventDto {
    private String annotation;
    private long category;
    private String description;
    private LocalDateTime eventDate;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private String title;

}
