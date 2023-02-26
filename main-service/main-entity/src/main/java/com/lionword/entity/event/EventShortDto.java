package com.lionword.entity.event;

import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.user.UserShortDto;
import java.time.LocalDateTime;


public class EventShortDto {
    private long id;
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private long views;
}
