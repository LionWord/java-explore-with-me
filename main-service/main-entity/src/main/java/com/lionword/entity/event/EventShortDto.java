package com.lionword.entity.event;

import com.lionword.entity.category.CategoryDto;
import com.lionword.entity.user.UserShortDto;
import java.time.LocalDateTime;

//Проекция
public interface EventShortDto {
    long getId();
    String getAnnotation() ;
    CategoryDto getCategory();
    long getConfirmedRequests();
    LocalDateTime getEventDate();
    UserShortDto getInitiator();
    Boolean getPaid();
    String getTitle();
    long getViews();
}
