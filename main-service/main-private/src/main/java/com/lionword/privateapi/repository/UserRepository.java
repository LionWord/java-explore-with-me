package com.lionword.privateapi.repository;

import com.lionword.entity.user.UserDto;
import com.lionword.privateapi.repository.limitedparents.LimitedUserRepository;

public interface UserRepository extends LimitedUserRepository<UserDto, Long> {
}
