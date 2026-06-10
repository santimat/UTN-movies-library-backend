package com.utntp.utnmovieslibrarybackend.controller.auth;

import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/me")
public class AuthVerifyUserController {

    public AuthVerifyUserController() {
    }

    @GetMapping
    public ResponseEntity<UserResponse> verifyAuth(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserResponse userResponse = new UserResponse(
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getRole().name()
        );

        return ResponseEntity.ok(userResponse);
    }
}
