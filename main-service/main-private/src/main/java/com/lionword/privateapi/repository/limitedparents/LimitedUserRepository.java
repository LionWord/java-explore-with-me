package com.lionword.privateapi.repository.limitedparents;


import com.lionword.entity.user.UserDto;
import com.lionword.entity.user.UserShortDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface LimitedUserRepository <UserDto, Long> extends Repository<UserDto, Long> {
        UserShortDto findById(long userId);
}
