package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.user.UserDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LimitedUserRepository<UserDto, Long> {
}
