package com.lionword.entity.event;

import com.lionword.entity.location.Location;

import java.time.LocalDateTime;

public class NewEventDto {
    //min - 20, max - 2000
    private String annotation;
    private long category;
    //min - 20, max - 7000
    private String description;
    private LocalDateTime eventDate;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    //min - 3, max - 120
    private String title;

}
