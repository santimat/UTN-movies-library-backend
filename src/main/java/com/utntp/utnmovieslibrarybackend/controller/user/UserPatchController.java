package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRoleRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.service.user.UserUpdaterRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserPatchController {
    private final UserUpdaterRoleService userUpdaterRoleService;
    private final UserMapper userMapper;

    public UserPatchController(UserUpdaterRoleService userUpdaterRoleService) {
        this.userUpdaterRoleService = userUpdaterRoleService;
        this.userMapper = new UserMapper();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable Long id,
                                                       @RequestBody UserRoleRequest userRoleRequest) {
        UserResponse userResponse = userMapper.toResponse(userUpdaterRoleService.updateRole(id, userRoleRequest));
        return ResponseEntity.ok(userResponse);
    }
}
