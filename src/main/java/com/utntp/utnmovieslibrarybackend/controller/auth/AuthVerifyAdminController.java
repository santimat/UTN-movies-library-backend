package com.utntp.utnmovieslibrarybackend.controller.auth;

import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.enums.UserRoleEnum;
import com.utntp.utnmovieslibrarybackend.service.jwt.JwtService;
import com.utntp.utnmovieslibrarybackend.service.user.UserExistingValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/admin")
public class AuthVerifyAdminController {
    private final UserExistingValidatorService userExistingValidatorService;
    private final JwtService jwtService;

    public AuthVerifyAdminController(UserExistingValidatorService userExistingValidatorService, JwtService jwtService) {
        this.userExistingValidatorService = userExistingValidatorService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> verifyAdmin(@CookieValue(name = "token") String token){
        if(token == null || token.isEmpty() || !jwtService.isTokenValid(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserRoleEnum userRole = jwtService.getRole(token);
        System.out.println("User role: " + userRole.toString());
        if(userRole != UserRoleEnum.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String email = jwtService.getEmail(token);
        if(!userExistingValidatorService.existsByEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(jwtService.getUserData(token));
    }
}
