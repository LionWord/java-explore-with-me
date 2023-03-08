package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.user.UserShortDto;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedUserRepository<UserDto, Long> extends Repository<UserDto, Long> {
    Optional<UserDto> findById(long userId);
}
