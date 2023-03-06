package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.user.UserShortDto;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface LimitedUserRepository<UserDto, Long> extends Repository<UserDto, Long> {
    UserDto findById(long userId);
}
