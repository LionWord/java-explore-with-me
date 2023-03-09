package com.lionword.mainservice.entity.event;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private UserDto initiator;
    private Boolean paid;
    private String title;
    private long views;
}
