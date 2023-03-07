package com.lionword.mainservice.adminapi.users.service;

import com.lionword.mainservice.adminapi.users.repository.AdminUserRepository;
import com.lionword.mainservice.apierror.exceptions.NotUniqueUsernameException;
import com.lionword.mainservice.entity.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository userRepository;

    public List<UserDto> getUsers(List<Long> ids, int from, int size) {
        return userRepository.findAllByIdIn(ids, PageRequest.of(from, size));
    }

    public UserDto createUser(UserDto user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new NotUniqueUsernameException(HttpStatus.CONFLICT,
                    "Not unique username",
                    "Username must be unique. Current value: " + user.getName());
        }
        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}
