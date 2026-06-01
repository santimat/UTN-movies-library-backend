package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.service.user.UserFinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserGetController {
    private final UserMapper userMapper;
    private final UserFinderService userFinderService;

    public UserGetController( UserFinderService userFinderService) {
        this.userFinderService = userFinderService;
        this.userMapper = new UserMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id){
        User user = userFinderService.find(id);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
