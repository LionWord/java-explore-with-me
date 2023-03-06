package com.lionword.mainservice.entity.event;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.user.UserDto;
import com.lionword.mainservice.entity.user.UserShortDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class EventShortDto {
    private long id;
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime eventDate;
    private UserDto initiator;
    private Boolean paid;
    private String title;
    private long views;
}
