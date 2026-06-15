package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthRegisterRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.service.user.UserUpdaterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserPutController {
    private final UserUpdaterService userUpdaterService;
    private final UserMapper userMapper;


    public UserPutController(UserUpdaterService userUpdaterService) {
        this.userUpdaterService = userUpdaterService;
        this.userMapper = new UserMapper();
    }

    @PutMapping("/{id}/posters")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long id,
                                            @Valid @RequestBody AuthRegisterRequest userRequest,
                                            @RequestParam(required = false) MultipartFile file){
        User user = userUpdaterService.updateById(id, userRequest, file);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
