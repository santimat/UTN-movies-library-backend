package com.utntp.utnmovieslibrarybackend.controller.auth;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthRegisterRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.service.auth.AuthRegisterService;
import com.utntp.utnmovieslibrarybackend.service.user.UserExistingValidatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/register")
public class AuthRegisterController {
    private final AuthRegisterService authRegisterService;
    private final UserExistingValidatorService userExistingValidatorService;
    private final UserMapper userMapper;


    public AuthRegisterController(AuthRegisterService authRegisterService,
                                  UserExistingValidatorService userExistingValidatorService) {
        this.authRegisterService = authRegisterService;
        this.userExistingValidatorService = userExistingValidatorService;
        this.userMapper = new UserMapper();
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody AuthRegisterRequest userRequest){
        if(userExistingValidatorService.existsByEmail(userRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = authRegisterService.register(userRequest);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
