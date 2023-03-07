package com.lionword.mainservice.adminapi.users.service;

import com.lionword.mainservice.adminapi.users.repository.AdminUserRepository;
import com.lionword.mainservice.entity.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository repo;

    public List<UserDto> getUsers(List<Long> ids, int from, int size) {
        return repo.findAllByIdIn(ids, PageRequest.of(from, size));
    }

    public UserDto createUser(UserDto user) {
        return repo.save(user);
    }

    public void deleteUser(long userId) {
        repo.deleteById(userId);
    }

}
