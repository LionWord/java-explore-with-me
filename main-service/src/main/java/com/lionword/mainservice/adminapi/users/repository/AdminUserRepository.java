package com.lionword.mainservice.adminapi.users.repository;

import com.lionword.mainservice.entity.user.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<UserDto, Long> {
}
