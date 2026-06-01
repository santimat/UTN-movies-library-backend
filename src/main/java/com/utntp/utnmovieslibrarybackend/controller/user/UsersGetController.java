package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.service.user.UsersSearcherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UsersGetController {
    private final UsersSearcherService usersSearcherService;
    private final UserMapper userMapper;


    public UsersGetController(UsersSearcherService usersSearcherService) {
        this.usersSearcherService = usersSearcherService;
        this.userMapper = new UserMapper();
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(@RequestParam int page,
                                                     @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = usersSearcherService.findAll(pageable);
        return ResponseEntity.ok(
                users.map(
                        userMapper::toResponse
                )
        );
    }
}
