package com.lionword.mainservice.privateapi.repository.limitedparents;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface LimitedPrivateUserRepository<UserDto, Long> extends Repository<UserDto, Long> {
    Optional<UserDto> findById(long userId);
}
