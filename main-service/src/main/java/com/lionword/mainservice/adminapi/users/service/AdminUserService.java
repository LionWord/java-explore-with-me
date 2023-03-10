package com.lionword.mainservice.adminapi.users.service;

import com.lionword.mainservice.entity.user.UserDto;

import java.util.List;

public interface AdminUserService {
    List<UserDto> getUsers(List<Long> ids, int from, int size);

    UserDto createUser(UserDto user);

    void deleteUser(long userId);

}
