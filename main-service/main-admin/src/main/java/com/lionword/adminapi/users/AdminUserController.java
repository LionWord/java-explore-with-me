package com.lionword.adminapi.users;

import com.lionword.adminapi.users.service.AdminUserService;
import com.lionword.entity.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "ids") List<Long> ids,
                                  @RequestParam(name = "from", defaultValue = "0") int from,
                                  @RequestParam(name = "from", defaultValue = "10") int size) {
        //stub
        return adminUserService.getUsers(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto user) {
        //stub
        return adminUserService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long userId) {
        adminUserService.deleteUser(userId);
    }
}
