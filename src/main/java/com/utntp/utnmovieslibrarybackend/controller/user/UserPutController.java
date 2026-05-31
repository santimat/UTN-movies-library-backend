package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.service.user.UserUpdaterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserPutController {
    private final UserUpdaterService userUpdaterService;
    private final UserMapper userMapper;


    public UserPutController(UserUpdaterService userUpdaterService, UserMapper userMapper) {
        this.userUpdaterService = userUpdaterService;
        this.userMapper = userMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> put(@PathVariable Long id,
                                            @Valid @RequestBody UserRequest userRequest){
        User user = userUpdaterService.update(id, userRequest);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
