package com.lionword.mainservice.adminapi.users.repository;

import com.lionword.mainservice.entity.user.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRepository extends JpaRepository<UserDto, Long> {
    List<UserDto> findAllByIdIn(List<Long> ids, Pageable pageable);
}
