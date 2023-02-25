package com.lionword.entity.event;

import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.location.Location;
import com.lionword.entity.user.UserShortDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class EventFullDto {
    @Id
    private long id;
    private String annotation;
    private CategoryDto category;
    private int confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private EventState state;
    private String title;
    private long views;

}
