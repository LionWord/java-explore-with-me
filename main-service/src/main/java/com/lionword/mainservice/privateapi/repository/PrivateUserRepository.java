package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.user.UserDto;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedPrivateUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateUserRepository extends LimitedPrivateUserRepository<UserDto, Long> {
}
