package com.lionword.entity.event;

import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.location.Location;
import com.lionword.entity.user.UserShortDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class EventFullDto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "annotation")
    private String annotation;
    @Column(name = "category")
    private CategoryDto category;
    @Column(name = "confirmed_requests")
    private int confirmedRequests;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "description")
    private String description;
    @Column(name = "event_date")
    private LocalDateTime eventDate;
    @Column(name = "initiator")
    private UserShortDto initiator;
    @Column(name = "location")
    private Location location;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "participant_limit")
    private int participantLimit;
    @Column(name = "published_on")
    private LocalDateTime publishedOn;
    @Column(name = "request_moderation")
    private boolean requestModeration;
    @Column(name = "state")
    private EventState state;
    @Column(name = "title")
    private String title;
    @Column(name = "views")
    private long views;

}
