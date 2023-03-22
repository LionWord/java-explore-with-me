package com.lionword.mainservice.entity.util;

import com.lionword.mainservice.entity.user.UserDto;
import com.lionword.mainservice.entity.user.UserShortDto;

public class UserMapper {
    public static UserShortDto mapToShort(UserDto user) {
        UserShortDto userShortDto = new UserShortDto();
        userShortDto.setName(user.getName());
        return userShortDto;
    }
}
