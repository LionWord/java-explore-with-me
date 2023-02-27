package com.lionword.adminapi.users.repository;

import com.lionword.entity.user.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<UserDto, Long> {
}
