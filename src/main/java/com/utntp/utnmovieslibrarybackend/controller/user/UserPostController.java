package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.service.user.UserCreatorService;
import com.utntp.utnmovieslibrarybackend.service.user.UserExistingValidatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserPostController {
    private final UserCreatorService userCreatorService;
    private final UserExistingValidatorService userExistingValidatorService;
    private final UserMapper userMapper;


    public UserPostController(UserCreatorService userCreatorService, UserExistingValidatorService userExistingValidatorService) {
        this.userCreatorService = userCreatorService;
        this.userExistingValidatorService = userExistingValidatorService;
        this.userMapper = new UserMapper();
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        if(userExistingValidatorService.existsByEmail(userRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userCreatorService.create(userRequest);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
